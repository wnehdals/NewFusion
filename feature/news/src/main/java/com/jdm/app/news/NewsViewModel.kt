package com.jdm.app.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdm.app.domain.usecase.GetNewsListUseCase
import com.jdm.app.model.CategoryCode
import com.jdm.app.model.ChipState
import com.jdm.app.model.DialogToken
import com.jdm.app.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {
    private val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
        intent {
            copy(
                dialogToken = DialogToken(
                    title = "${throwable.message}",
                    rightTextRes = R.string.str_confirm
                )
            )
        }
    }
    /* UI State */
    private val _uiState = MutableStateFlow(NewsState())
    val uiState = _uiState.asStateFlow()

    /* SideEffect */
    private val _sideEffect: Channel<NewsSideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    /* UI Event */
    private val _event: MutableSharedFlow<NewsEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    /* 현재 UI State */
    val currentState: NewsState
        get() = _uiState.value



    private var subscriptionJob: Job? = null

    init {
        subscribeEvents()
    }

    /**
     * @param reduce 현재 UI State
     * UI Update 함수
     */
    fun intent(reduce: NewsState.() -> NewsState) {
        val state = currentState.reduce()
        _uiState.update { state }
    }

    /**
     * @param builder SideEffect Builder
     * SideEffect를 발생시키는 함수
     */
    fun postSideEffect(vararg builder: NewsSideEffect) {
        for (effectValue in builder) {
            viewModelScope.launch { _sideEffect.send(effectValue) }
        }
    }

    /**
     * 초기 데이터 설정
     * 오늘 날짜 설정
     * News 데이터 호출 시간 설정
     * News 데이터 10개 호출 이후 10초마다 News 데이터 호출
     */
    fun initData() {
        var currentTime = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
        val timeFormat = SimpleDateFormat("HH:mm:ss")
        getNewsListUseCase.invoke(
            cursor = currentState.cursor,
            currentTime = currentTime
        ).onEach {
            intent {
                copy(
                    today = dateFormat.format(currentTime),
                    news = it.data.toPersistentList(),
                    totalNews = it.data.toPersistentList(),
                    filteredNews = getFilteredList(news = it.data).toPersistentList(),
                    updateTime = timeFormat.format(currentTime),
                    cursor = it.cursor,
                )
            }
        }.catch {
            intent {
                copy(
                    dialogToken = DialogToken(
                        title = "${it.message}",
                        rightTextRes = R.string.str_confirm
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

    /**
     * 10초마다 News 데이터를 호출
     * 서버로 부터 받은 News 데이터는 tataolNews에 추가
     * 오늘 날짜 업데이트
     * News 데이터 호출 시간 업데이트
     */
    fun getNewsContentsSubscribe() {
        /* 중복 호출 방지 */
        if (subscriptionJob?.isActive == true) return

        subscriptionJob = viewModelScope.launch(ceh) {
            while (true) {
                delay(10 * 1000L)
                var currentTime = System.currentTimeMillis()
                val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
                val timeFormat = SimpleDateFormat("HH:mm:ss")
                getNewsListUseCase.invoke(
                    cursor = currentState.cursor,
                    currentTime = currentTime
                ).collect {
                    intent {
                        val newNews = currentState.totalNews.toMutableList()
                        newNews.addAll(it.data)
                        newNews.sort()
                        copy(
                            today = dateFormat.format(currentTime),
                            totalNews = newNews.toPersistentList(),
                            updateTime = timeFormat.format(currentTime),
                            cursor = it.cursor,
                            updatedNewsCnt = currentState.updatedNewsCnt + it.data.size
                        )
                    }
                }
                if (currentState.cursor == null) break


            }

        }

    }

    /**
     * @param event UI Event
     * UI 이벤트를 받는 함수
     */
    fun setEvent(event: NewsEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    /**
     * UI 이벤트를 구독하는 함수
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * @param event UI Event
     * UI 이벤트를 처리하는 함수
     */
    private fun handleEvent(event: NewsEvent) {
        when (event) {
            /* 카테고리 버튼 클릭 이벤트 */
            is NewsEvent.OnClickCategory -> {
                val category = currentState.category.map {
                    it.copy(
                        isChecked = it.id == event.chipState.id
                    )
                }

                val newNews = getFilteredList(category = event.chipState)

                intent {
                    copy(
                        category = category,
                        filteredNews = newNews.toPersistentList()
                    )
                }
            }
            /* 필터 버튼 클릭 이벤트 */
            is NewsEvent.OnClickSubjectFilter -> {
                intent {
                    copy(
                        subjectDialogToken = SubjectDialogToken(
                            subjects = this.subjects.toPersistentList()
                        )
                    )
                }
            }
            /* 주제 필터 다이얼로그 닫기 버튼 클릭 이벤트 */
            is NewsEvent.OnSubjectBottomDialogDismiss -> {
                intent {
                    copy(
                        subjectDialogToken = null
                    )
                }
            }
            /* 주제 필터 다이얼로그 닫기 버튼 클릭 이벤트 */
            is NewsEvent.OnConfirmDialogDismiss -> {
                intent {
                    copy(
                        dialogToken = null
                    )
                }
            }
            /* 주제 필터 다이얼로그 초기화 버튼 클릭 이벤트 */
            is NewsEvent.OnClickInitialize -> {
                intent {
                    copy(
                        subjectDialogToken = null,
                        subjects = this.subjects.map { it.copy(isChecked = true) }
                            .toPersistentList(),
                        filteredNews = getFilteredList(subjects = this.subjects.map {
                            it.copy(
                                isChecked = true
                            )
                        }).toPersistentList()
                    )
                }
            }
            /* 주제 필터 다이얼로그 선택 완료 버튼 클릭 이벤트 */
            is NewsEvent.OnClickComplete -> {
                intent {
                    copy(
                        filteredNews = getFilteredList(subjects = event.chipStates).toPersistentList(),
                        subjectDialogToken = null,
                        subjects = event.chipStates.toPersistentList()
                    )
                }
            }
            /*  새로운 뉴스 버튼 클릭 이벤트 */
            is NewsEvent.OnClickNewNews -> {
                val visibleNewsId = currentState.news.map { it.id }
                val newNews = getFilteredList(news = currentState.totalNews).map {
                    it.copy(isNew = !visibleNewsId.contains(it.id))
                }
                intent {
                    copy(
                        updatedNewsCnt = 0,
                        filteredNews = newNews.toPersistentList(),
                        news = currentState.totalNews
                            .map {
                                it.copy(isNew = !visibleNewsId.contains(it.id))
                            }.toPersistentList()
                    )
                }
            }
        }
    }

    /**
     * @param news News 데이터
     * @param category News 데이터의 Category를 필터할 Category
     * @param subjects News 데이터의 Subject를 필터할 Subject 리스트
     * @return category와 subjects로 필터링된 News 데이터
     */
    private fun getFilteredList(
        news: List<News>? = null,
        category: ChipState? = null,
        subjects: List<ChipState>? = null
    ): List<News> {
        val notNullNewsList: MutableList<News> =
            news?.toMutableList() ?: currentState.news.toMutableList()
        notNullNewsList.sort()
        val checkedCategory = category ?: currentState.category.first { it.isChecked }
        val checkedCategoryCode: CategoryCode = CategoryCode.find(checkedCategory.id)


        val categoryFilteredNews =
            if (checkedCategoryCode == CategoryCode.ALL) notNullNewsList
            else notNullNewsList.filter { CategoryCode.find(it.category) == checkedCategoryCode }


        val subjectsFilterValue = subjects ?: currentState.subjects
        val subjectedFilter = subjectsFilterValue.filter { it.isChecked }.map { it.id }

        val subjectedFilteredNews = categoryFilteredNews.filter {
            it.subject in subjectedFilter
        }

        return subjectedFilteredNews

    }
}
