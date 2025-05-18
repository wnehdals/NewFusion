package com.jdm.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResp(
    @SerialName("category")
    val category: String? = "",
    @SerialName("subject")
    val subject: String? = "",
    @SerialName("title")
    val title: String? = "",
    @SerialName("content")
    val content: String? = "",
    @SerialName("source")
    val source: String? = ""
)
