package com.jdm.app.data.di

import android.content.Context
import com.jdm.app.data.api.NewsApi
import com.jdm.app.data.api.NewsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun newsApi(@ApplicationContext context: Context, json: Json): NewsApi {
        return NewsApiImpl(context, json)
    }
}