package com.jdm.app.model

enum class CategoryCode {
    ALL, IMPORTANCE, STOCK, ECONOMIC, NEWS;
    companion object {
        fun find(str: String?): CategoryCode {
            return when (str) {
                "전체" -> ALL
                "중요" -> IMPORTANCE
                "주식" -> STOCK
                "경제 발표" -> ECONOMIC
                "뉴스" -> NEWS
                else -> ALL
            }
        }
    }
}