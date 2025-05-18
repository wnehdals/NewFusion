package com.jdm.app.model

import kotlinx.serialization.SerialName

data class News(
    val id: Int,
    val category: String = "",
    val subject: String = "",
    val title: String = "",
    val content: String = "",
    val source: String = "",
    val date: Long = 0L,
    val isNew: Boolean = false
): Comparable<News> {
    override fun compareTo(other: News): Int {
        return when {
            this.date > other.date -> -1
            this.date < other.date -> 1
            else -> 0
        }
    }
}
