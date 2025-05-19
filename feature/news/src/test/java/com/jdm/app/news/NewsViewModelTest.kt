package com.jdm.app.news

import com.jdm.app.domain.usecase.GetNewsListUseCase
import com.jdm.app.model.ChipState
import com.jdm.app.navigation.MainDispatcherRule
import com.jdm.app.navigation.TestNewsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val newsRepository = TestNewsRepository()
    private lateinit var viewModel: NewsViewModel
    private var getNewsListUseCase: GetNewsListUseCase = GetNewsListUseCase(newsRepository = newsRepository)
    val category: List<ChipState> = listOf(
        ChipState("전체",R.string.str_all,true),
        ChipState("중요",R.string.str_importance,false),
        ChipState("주식",R.string.str_stock,false),
        ChipState("경제 발표",R.string.str_economic_presentation,false),
        ChipState("뉴스",R.string.str_news,false)
    )
    @Before
    fun setup() {
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )
    }

    @Test
    fun uiState_categrory_isChecked_success() = runTest {
        viewModel.setEvent(NewsEvent.OnClickCategory(category[4]))
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        assertEquals(true, viewModel.uiState.value.category[4].isChecked)
    }

    @Test
    fun uiState_categrory_news_isChecked_true() = runTest {
        viewModel.t()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            //getNewsListUseCase.invoke(null, System.currentTimeMillis()).
            viewModel.uiState.collect {
                val a = it.toString()
            }
        }
        viewModel.t()
        assertEquals(20, viewModel.uiState.value.filteredNews.size)
    }


}