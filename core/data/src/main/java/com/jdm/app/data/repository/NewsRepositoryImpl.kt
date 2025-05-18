package com.jdm.app.data.repository

import android.util.Log
import com.jdm.app.data.api.NewsApi
import com.jdm.app.data.mapper.toDomain
import com.jdm.app.model.News
import com.jdm.app.model.NewsPage
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    private var cachedNewsList: List<News> = emptyList()
    /**
     * newsApi로 부터 Nesw 데이터를 가져와 id와 시간을 설정하고 cursor값을 지정하여 NewsPage를 반환하는 함수
     * @param cursor cachedNewsList에서 pageSize만큼 가져올 데이터의 첫번째 id
     * @param currentTime 현재시간
     * @param pageSize cachedNewsList에서 가져올 데이터 개수
     * @return  NewsPage
     */
    override suspend fun getNewsContents(cursor: Int?, currentTime: Long, pageSize: Int): NewsPage {
        if (cachedNewsList.isEmpty()) {
            cachedNewsList = newsApi.getNewsContents()
                .mapIndexed { index, newsResp ->  newsResp.toDomain(index) }
        }

        val filterList = if (cursor == null) cachedNewsList.toList() else cachedNewsList.filter { it.id > cursor }
        val newsData = if (filterList.size < pageSize) {
            filterList.mapIndexed { index, it ->
                it.copy(date = currentTime + (index * 1000L))
            }
        } else {
            filterList.take(pageSize)
                .mapIndexed { index, it ->
                    it.copy(date = currentTime + (index * 1000L))
                }
        }
        return NewsPage(
            data = newsData,
            cursor = newsData.lastOrNull()?.id
        )

    }
}