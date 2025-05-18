package com.jdm.app.data.api

import com.jdm.app.data.model.NewsResp

interface NewsApi {
    suspend fun getNewsContents(): List<NewsResp>
}