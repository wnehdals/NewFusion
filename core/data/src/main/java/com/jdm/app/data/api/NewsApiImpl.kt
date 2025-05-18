package com.jdm.app.data.api

import android.content.Context
import com.jdm.app.data.model.NewsResp
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

class NewsApiImpl(
    private val context: Context,
    private val json: Json
): NewsApi {
    override suspend fun getNewsContents(): List<NewsResp> {
        val source = context.assets.open("news.json")
        return json.decodeFromStream(source)
    }
}