package com.jdm.app.model

data class NewsPage(
    val data: List<News>,
    val cursor: Int?
)
