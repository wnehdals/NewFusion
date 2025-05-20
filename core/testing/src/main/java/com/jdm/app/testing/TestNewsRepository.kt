package com.jdm.app.testing

import com.jdm.app.data.repository.NewsRepository
import com.jdm.app.model.News
import com.jdm.app.model.NewsPage

class TestNewsRepository : NewsRepository {
    var testCase: TestCase = TestCase.SUCCESS
    val exceptionMsg: String = "exception test message"
    val testData = listOf(
        News(
            id = 10,
            category = "중요",
            subject = "시장 분석",
            title = "외환 시장 변동성 확대",
            content = "계량적 모델 분석 결과, 국제 외환 시장은(는) 소비자 신뢰지수 변화으로 인해 변동성이 큰 흐름을 보이고 있습니다 . 투자자들은 향후1 분기 동안 높은 변동성 지속에 대비해야 할 것입니다.",
            source = "KDI 경제전망",
            date = 1747666881281L,
            isNew = false
        ),
        News(
            id = 11,
            category = "뉴스",
            subject = "지정학",
            title = "아태지역 정상회담 개최",
            content = "아시아 태평양 지역의 지정학적 주요국 정상회담 개최 소식이 전해지면서 글로벌 금융시장에 긍정적인 영향을 주고 있습니다 . 글로벌 교역량 변동이 (가) 이번 사태의 주요 배경으로 분석됩니다.",
            source = "세계경제포럼",
            date = 1747666882281L,
            isNew = false
        ),
        News(
            id = 12,
            category = "경제 발표",
            subject = "아시아 경제지표",
            title = "아시아 소매판매 급등",
            content = "아시아 경제지표 관련 월간 소매판매 동향이 최근 발표되어, 전월 대비 3.5 % 급등세를 나타내며 기존 전망치를 크게 웃돌았습니다 . 이는 현재 거시경제 상황에 대한 긍정적인 시그널로 해석됩니다.",
            source = "아시아개발은행",
            date = 1747666883281L,
            isNew = false
        ),
        News(
            id = 13,
            category = "주식",
            subject = "유럽 주식",
            title = "유럽 주식 급등 기록",
            content = "유럽 주식 시장은 시장 참여자들이 선별적인 투자 확대에 따라 이례적인 급등을 기록했습니다 . 특히 신재생에너지 관련 기업 분야가 점진적인 추세를 이어가고 있습니다 . 기업들의 실적 호조 등이 시장 분위기를 좌우했습니다 .",
            source = "로이터Biz",
            date = 1747666884281L,
            isNew = false
        ),
        News(
            id = 14,
            category = "중요",
            subject = "무역",
            title = "중국과 무역 협정 체결",
            content = "중국과의 무역 관련 새로운 공급망 협정 체결이 핵심 이슈로 부상하며, 국제 교역 질서에 주목할 만한 상황을 연출하고 있습니다 . 관련 산업계의 면밀한 검토가 필요합니다 .",
            source = "OPEC 인사이트",
            date = 1747666885281L,
            isNew = false
        ),
        News(
            id = 15,
            category = "뉴스",
            subject = "에너지",
            title = "LNG 가격 안정세 유지",
            content = "액화천연가스(LNG) 가격이 국제 원자재 가격 급등에 따라 안정적인 수준을 유지했습니다 . 이는 글로벌 에너지 시장의 패러다임 변화를 예고하며 혼조세의 움직임을 나타내고 있습니다 .",
            source = "유로 Stoxx 뉴스",
            date = 1747666886281L,
            isNew = false
        ),
        News(
            id = 16,
            category = "경제 발표",
            subject = "미국 경제지표",
            title = "미 PPI 예상 하회",
            content = "미국 경제지표 관련 생산자물가지수 (PPI) 가 최근 발표되어, 전월 대비 0.3 % 예상외로 급락하며 예상보다 다소 부진했습니다.이는 현재 거시경제 상황에 대한 불확실한 시그널로 해석됩니다 .",
            source = "기재부 발표",
            date = 1747666887281L,
            isNew = false
        ),
        News(
            id = 17,
            category = "주식",
            subject = "한국 주식",
            title = "한국 주식 약보합 마감",
            content = "한국 주식 시장은 외국인 투자자들이 신중한 차익 실현에 따라 보합권에서 등락을 거듭했습니다 . 특히 필수소비재 업종 분야가 부정적인 결과를 도출했습니다 . 지정학적 리스크 고조 등이 시장 분위기를 좌우했습니다 .",
            source = "연합경제",
            date = 1747666888281L,
            isNew = false
        ),
        News(
            id = 18,
            category = "중요",
            subject = "시장 분석",
            title = "원자재 시장 강세 전망",
            content = "투자 심리 지표를 보면, 국제 금 가격은(는) 혁신 기술의 등장으로 인해 긍정적인 양상을 띠고 있습니다.투자자들은 향후 3 분기 동안 점진적 회복세에 대비해야 할 것입니다 .",
            source = "국제금융센터",
            date = 1747666889281L,
            isNew = false
        ),
        News(
            id = 19,
            category = "뉴스",
            subject = "지정학",
            title = "사하라 이남 분쟁 심화",
            content = "사하라 이남 아프리카 지역의 지정학적 경제 제재 조치 발표 소식이 전해지면서, 글로벌 금융시장에 부정적인 상황을 연출하고 있습니다 . 소비자 신뢰지수 변화이 (가) 이번 사태의 주요 배경으로 분석됩니다 .",
            source = "통계청 자료",
            date = 1747666890281L,
            isNew = false
        ),
        News(
            id = 20,
            category = "경제 발표",
            subject = "유럽 경제지표",
            title = "유럽 수출 증가율 발표",
            content = "유럽 경제지표 관련 수출 증가율이 최근 발표되어, 전월 대비 1.1 % 소폭 증가하며 일부 우려를 완화시켰습니다.이는 현재 거시경제 상황에 대한 점진적인 시그널로 해석됩니다 . 전문가들은 이에 대해 신중한 낙관론을 내놓고 있습니다 .",
            source = "미 상무부 발표",
            date = 1747666891296,
            isNew = false
        ),
        News(
            id = 21,
            category = "주식",
            subject = "미국 주식",
            title = "미국 기술주 조정 국면",
            content = "미국 주식 시장은 개인 투자자들이 공격적인 매수 전략에 따라 하락하며 거래를 마쳤습니다 . 특히 대형 IT 플랫폼 분야가 미미한 영향을 주고 있습니다 . 글로벌 교역량 변동 등이 시장 분위기를 좌우했습니다 .",
            source = "블룸버그K",
            date = 1747666892296,
            isNew = false
        ),
        News(
            id = 22,
            category = "중요",
            subject = "에너지",
            title = "태양광 효율 획기적 발전",
            content = "태양광 모듈 효율이 혁신 기술의 등장에 따라 획기적인 발전을 이루었습니다 . 이는 글로벌 에너지 시장의 패러다임 변화를 예고하며 긍정적인 추세를 이어가고 있습니다 .",
            source = "로이터Biz",
            date = 1747666893296,
            isNew = false
        ),
        News(
            id = 23,
            category = "뉴스",
            subject = "무역",
            title = "EU와 공급망 협력 강화",
            content = "EU와 영국 간의 무역 관련 공급망 다변화 전략이 핵심 이슈로 부상하며, 국제 교역 질서에 안정적인 결과를 도출했습니다 . 관련 산업계의 면밀한 검토가 필요합니다 .",
            source = "무역협회 자료",
            date = 1747666894296,
            isNew = false
        ),
        News(
            id = 24,
            category = "경제 발표",
            subject = "한국 경제지표",
            title = "한국 건설수주 급락",
            content = "한국 경제지표 관련 건설경기실사지수 (CBSI) 가 최근 발표되어, 전월 대비 3.8 % 예상외로 급락하며 기존 전망치를 크게 웃돌았습니다 . 이는 현재 거시경제 상황에 대한 부정적인 시그널로 해석됩니다.",
            source = "KDI 경제전망",
            date = 1747666895296,
            isNew = false
        ),
        News(
            id = 25,
            category = "주식",
            subject = "아시아 주식",
            title = "아시아 증시 변동성 확대",
            content = "아시아 주식 시장은 시장 참여자들이 뚜렷한 관망 태도에 따라 큰 폭으로 하락하며 장을 마쳤습니다 . 특히 AI 관련 기술주 분야가 불확실한 양상을 띠고 있습니다 . 금리 정책 변화 등이 시장 분위기를 좌우했습니다 .",
            source = "파이낸셜K",
            date = 1747666896296,
            isNew = false
        ),
        News(
            id = 26,
            category = "중요",
            subject = "지정학",
            title = "대만해협 긴장 고조",
            content = "대만 해협 지역의 지정학적 국제적 갈등 심화 소식이 전해지면서, 글로벌 금융시장에 변동성이 큰 움직임을 나타내고 있습니다.기업들의 실적 호조이(가) 이번 사태의 주요 배경으로 분석됩니다 .",
            source = "월스트리트N",
            date = 1747666897296,
            isNew = false
        ),
        News(
            id = 27,
            category = "뉴스",
            subject = "시장 분석",
            title = "대체투자 시장 전망",
            content = "글로벌 투자 전략가들은, 대체투자 상품은 (는) 국제 원자재 가격 급등으로 인해 점진적인 흐름을 보이고 있습니다 . 투자자들은 향후2 분기 동안 횡보 장세에 대비해야 할 것입니다 .",
            source = "아시아개발은행",
            date = 1747666898296,
            isNew = false
        ),
        News(
            id = 28,
            category = "경제 발표",
            subject = "아시아 경제지표",
            title = "아시아 PMI 개선",
            content = "아시아 경제지표 관련 제조업 구매관리자지수(PMI) 가 최근 발표되어, 전월 대비 1.0 % 소폭 증가하며 대부분의 예측과 일치했습니다.이는 현재 거시경제 상황에 대한 뚜렷한 시그널로 해석됩니다 .",
            source = "ECB 통계",
            date = 1747666899296,
            isNew = false
        ),
        News(
            id = 29,
            category = "주식",
            subject = "유럽 주식",
            title = "유럽 증시 반등 성공",
            content = "유럽 주식 시장은 외국인 투자자들이 선별적인 투자 확대에 따라 상승 마감했습니다.특히 은행 및 금융주 분야가 부정적인 추세를 이어가고 있습니다.정부의 새로운 규제안 등이 시장 분위기를 좌우했습니다.",
            source = "세계경제포럼",
            date = 1747666900296,
            isNew = false
        ),
    )
    override suspend fun getNewsContents(
        cursor: Int?,
        currentTime: Long,
        pageSize: Int
    ): NewsPage {
        if (testCase == TestCase.SUCCESS) {
            return NewsPage(
                data = testData,
                cursor = 1
            )
        } else {
            throw NullPointerException(exceptionMsg)
        }
    }
}