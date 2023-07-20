package com.example.newsapp.di

import android.annotation.SuppressLint
import com.example.newsapp.network.NewsApiService
import com.example.newsapp.repositories.NewsRepository
import com.example.newsapp.repositories.NewsRepositoryImpl
import com.example.newsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @SuppressLint("SuspiciousIndentation")
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            return retrofit.create(NewsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService) : NewsRepository {
        return NewsRepositoryImpl(newsApiService)
    }
}

