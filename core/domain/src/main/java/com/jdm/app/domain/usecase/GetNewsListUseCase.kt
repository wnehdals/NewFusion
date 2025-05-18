package com.jdm.app.domain.usecase

import com.jdm.app.data.repository.NewsRepository
import com.jdm.app.model.News
import com.jdm.app.model.NewsPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(cursor: Int?, currentTime: Long, pageSize: Int = 10): Flow<NewsPage> = flow {
        emit(newsRepository.getNewsContents(cursor = cursor, currentTime = currentTime, pageSize = pageSize))
    }
}