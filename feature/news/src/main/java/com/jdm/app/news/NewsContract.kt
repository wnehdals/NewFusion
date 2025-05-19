package com.jdm.app.news

import com.jdm.app.model.ChipState
import com.jdm.app.model.DialogToken
import com.jdm.app.model.News
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class NewsState(
    val cursor: Int? = null,                                                            // 호출할 다음 뉴스 데이터의 첫번째 id
    val filteredNews: ImmutableList<News> = persistentListOf(),                         // 필터를 거친 뉴스
    val news: ImmutableList<News> = persistentListOf(),                                                    // 갱신 전 전체 뉴스
    val totalNews: ImmutableList<News> = persistentListOf(),                            // 갱신 후 전체 뉴스
    val category: List<ChipState> = listOf(                                             // 카테고리
        ChipState("전체",R.string.str_all,true),
        ChipState("중요",R.string.str_importance,false),
        ChipState("주식",R.string.str_stock,false),
        ChipState("경제 발표",R.string.str_economic_presentation,false),
        ChipState("뉴스",R.string.str_news,false)
    ),
    val subjects: ImmutableList<ChipState> = persistentListOf(                          // 주제
        ChipState("미국 주식",R.string.str_subject_01, true),
        ChipState("한국 주식",R.string.str_subject_02,true),
        ChipState("아시아 주식",R.string.str_subject_03,true),
        ChipState("유럽 주식",R.string.str_subject_04,true),
        ChipState("미국 경제지표",R.string.str_subject_05,true),
        ChipState("한국 경제지표",R.string.str_subject_06,true),
        ChipState("아시아 경제지표",R.string.str_subject_07,true),
        ChipState("유럽 경제지표",R.string.str_subject_08,true),
        ChipState("시장 분석",R.string.str_subject_09,true),
        ChipState("지정학",R.string.str_subject_10,true),
        ChipState("무역",R.string.str_subject_11,true),
        ChipState("에너지",R.string.str_subject_12,true),
        ),
    val subjectDialogToken: SubjectDialogToken? = null,                                 // 주제 다이얼로그 상태
    val dialogToken: DialogToken? = null,                                               // 일반 다이얼로그 상태
    val today: String = "",                                                             // 오늘 날짜
    val updateTime: String = "",                                                        // 데이터 갱신 날짜
    val updatedNewsCnt: Int = 0                                                         // 갱신된 누적 데이터 개수
)
sealed class NewsSideEffect {
    data object ScrollUp : NewsSideEffect()
}
sealed class NewsEvent {
    data class OnClickCategory(val chipState: ChipState): NewsEvent()
    data object OnClickInitialize: NewsEvent()
    data class OnClickComplete(val chipStates: List<ChipState>): NewsEvent()
    data object OnSubjectBottomDialogDismiss: NewsEvent()
    data object OnConfirmDialogDismiss: NewsEvent()
    data object OnClickSubjectFilter: NewsEvent()
    data object OnClickNewNews: NewsEvent()

}
data class SubjectDialogToken(
    val subjects: ImmutableList<ChipState> = persistentListOf(
        ChipState("미국 주식",R.string.str_subject_01, false),
        ChipState("한국 주식",R.string.str_subject_02,false),
        ChipState("아시아 주식",R.string.str_subject_03,false),
        ChipState("유럽 주식",R.string.str_subject_04,false),
        ChipState("미국 경제지표",R.string.str_subject_05,false),
        ChipState("한국 경제지표",R.string.str_subject_06,false),
        ChipState("아시아 경제지표",R.string.str_subject_07,false),
        ChipState("유럽 경제지표",R.string.str_subject_08,false),
        ChipState("시장 분석",R.string.str_subject_09,false),
        ChipState("지정학",R.string.str_subject_10,false),
        ChipState("무역",R.string.str_subject_11,false),
        ChipState("에너지",R.string.str_subject_12,false),
    )
)
