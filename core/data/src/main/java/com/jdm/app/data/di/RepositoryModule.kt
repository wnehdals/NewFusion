package com.jdm.app.data.di

import com.jdm.app.data.repository.NewsRepository
import com.jdm.app.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun newsRepository(repository: NewsRepositoryImpl): NewsRepository

}