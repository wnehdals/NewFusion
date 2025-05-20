package com.jdm.app.news

import com.jdm.app.domain.usecase.GetNewsListUseCase
import com.jdm.app.model.ChipState
import com.jdm.app.testing.MainDispatcherRule
import com.jdm.app.testing.TestCase
import com.jdm.app.testing.TestNewsRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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

    @Before
    fun setup() {
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )
    }


    @Test
    fun uiState_initData_success() = runTest {
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }
        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        assertEquals(20, viewModel.uiState.value.totalNews.size)
        assertEquals(20, viewModel.uiState.value.filteredNews.size)
    }

    @Test
    fun uiState_initData_fail() = runTest {
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }
        newsRepository.testCase = TestCase.FAIL
        viewModel.initData()
        assertEquals(newsRepository.exceptionMsg, viewModel.uiState.value.dialogToken?.title)
    }

    @Test
    fun uiState_categrory_click_event() = runTest {
        val category: List<ChipState> = listOf(
            ChipState("전체",R.string.str_all,true),
            ChipState("중요",R.string.str_importance,false),
            ChipState("주식",R.string.str_stock,false),
            ChipState("경제 발표",R.string.str_economic_presentation,false),
            ChipState("뉴스",R.string.str_news,false)
        )
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        viewModel.setEvent(NewsEvent.OnClickCategory(category[4]))

        val expectedResult = newsRepository.testData.filter { it.category == category[4].id }.size
        assertEquals(true, viewModel.uiState.value.category[4].isChecked)
        assertEquals(expectedResult, viewModel.uiState.value.filteredNews.size)
    }

    @Test
    fun uiState_subject_filter_dialog_click_event() = runTest {
        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        viewModel.setEvent(NewsEvent.OnClickSubjectFilter)

        assertNotNull(viewModel.uiState.value.subjectDialogToken)
    }

    @Test
    fun uiState_subject_filter_complete_click_event() = runTest {
        val subjects: ImmutableList<ChipState> = persistentListOf(
            ChipState("미국 주식",R.string.str_subject_01, true),
            ChipState("한국 주식",R.string.str_subject_02,true),
            ChipState("아시아 주식",R.string.str_subject_03,true),
            ChipState("유럽 주식",R.string.str_subject_04,true),
            ChipState("미국 경제지표",R.string.str_subject_05,true),
            ChipState("한국 경제지표",R.string.str_subject_06,true),
            ChipState("아시아 경제지표",R.string.str_subject_07,true),
            ChipState("유럽 경제지표",R.string.str_subject_08,true),
            ChipState("시장 분석",R.string.str_subject_09,false),
            ChipState("지정학",R.string.str_subject_10,false),
            ChipState("무역",R.string.str_subject_11,false),
            ChipState("에너지",R.string.str_subject_12,false),
        )

        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        viewModel.setEvent(NewsEvent.OnClickSubjectFilter)
        assertNotNull(viewModel.uiState.value.subjectDialogToken)

        viewModel.setEvent(NewsEvent.OnClickComplete(subjects))

        assertNull(viewModel.uiState.value.subjectDialogToken)
        val checkedSubjectList = subjects.filter { it.isChecked }.map { it.id }
        val expectedResult = newsRepository.testData.filter { it.subject in checkedSubjectList }.size


        assertEquals(expectedResult, viewModel.uiState.value.filteredNews.size)
    }

    @Test
    fun uiState_category_and_subject_filter_complete_click_event() = runTest {
        val category: List<ChipState> = listOf(
            ChipState("전체",R.string.str_all,true),
            ChipState("중요",R.string.str_importance,false),
            ChipState("주식",R.string.str_stock,false),
            ChipState("경제 발표",R.string.str_economic_presentation,false),
            ChipState("뉴스",R.string.str_news,false)
        )
        val subjects: ImmutableList<ChipState> = persistentListOf(
            ChipState("미국 주식",R.string.str_subject_01, false),
            ChipState("한국 주식",R.string.str_subject_02,true),
            ChipState("아시아 주식",R.string.str_subject_03,true),
            ChipState("유럽 주식",R.string.str_subject_04,true),
            ChipState("미국 경제지표",R.string.str_subject_05,true),
            ChipState("한국 경제지표",R.string.str_subject_06,true),
            ChipState("아시아 경제지표",R.string.str_subject_07,true),
            ChipState("유럽 경제지표",R.string.str_subject_08,true),
            ChipState("시장 분석",R.string.str_subject_09,false),
            ChipState("지정학",R.string.str_subject_10,false),
            ChipState("무역",R.string.str_subject_11,false),
            ChipState("에너지",R.string.str_subject_12,false),
        )

        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        viewModel.setEvent(NewsEvent.OnClickCategory(category[2]))
        viewModel.setEvent(NewsEvent.OnClickSubjectFilter)
        assertNotNull(viewModel.uiState.value.subjectDialogToken)

        viewModel.setEvent(NewsEvent.OnClickComplete(subjects))

        assertNull(viewModel.uiState.value.subjectDialogToken)


        val checkedSubjectList = subjects.filter { it.isChecked }.map { it.id }
        val expectedResult = newsRepository.testData
            .filter { it.category == category[2].id }
            .filter { it.subject in checkedSubjectList }
            .size


        assertEquals(expectedResult, viewModel.uiState.value.filteredNews.size)
    }

    @Test
    fun uiState_subject_filter_initialize_click_event() = runTest {
        val subjects: ImmutableList<ChipState> = persistentListOf(
            ChipState("미국 주식",R.string.str_subject_01, false),
            ChipState("한국 주식",R.string.str_subject_02,true),
            ChipState("아시아 주식",R.string.str_subject_03,true),
            ChipState("유럽 주식",R.string.str_subject_04,false),
            ChipState("미국 경제지표",R.string.str_subject_05,true),
            ChipState("한국 경제지표",R.string.str_subject_06,false),
            ChipState("아시아 경제지표",R.string.str_subject_07,true),
            ChipState("유럽 경제지표",R.string.str_subject_08,true),
            ChipState("시장 분석",R.string.str_subject_09,false),
            ChipState("지정학",R.string.str_subject_10,true),
            ChipState("무역",R.string.str_subject_11,true),
            ChipState("에너지",R.string.str_subject_12,true),
        )

        viewModel = NewsViewModel(
            getNewsListUseCase = getNewsListUseCase,
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect {}
        }

        newsRepository.testCase = TestCase.SUCCESS
        viewModel.initData()
        viewModel.setEvent(NewsEvent.OnClickSubjectFilter)
        assertNotNull(viewModel.uiState.value.subjectDialogToken)

        viewModel.setEvent(NewsEvent.OnClickComplete(subjects))

        assertNull(viewModel.uiState.value.subjectDialogToken)

        viewModel.setEvent(NewsEvent.OnClickSubjectFilter)
        viewModel.setEvent(NewsEvent.OnClickInitialize)


        assertEquals(subjects.size, viewModel.uiState.value.subjects.filter { it.isChecked }.size)
    }
    @Test
    fun uiStateick_event() = runTest {
        assertEquals(4, 2+2)
    }
}
