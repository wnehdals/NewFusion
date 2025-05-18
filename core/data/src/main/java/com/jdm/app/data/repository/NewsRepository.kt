package com.jdm.app.data.repository

import com.jdm.app.data.model.NewsResp
import com.jdm.app.model.News
import com.jdm.app.model.NewsPage

interface NewsRepository {
    suspend fun getNewsContents(cursor: Int?, currentTime: Long, pageSize: Int): NewsPage
}