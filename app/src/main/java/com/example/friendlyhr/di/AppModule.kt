package com.example.friendlyhr.di

import com.example.friendlyhr.data.FriendlyRepository
import com.example.friendlyhr.data.FriendlyRepositoryImpl
import com.example.friendlyhr.network.ApiService
import com.example.friendlyhr.network.httpClientAndroid
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return httpClientAndroid
    }

    @Provides
    fun provideApiService(httpClient: HttpClient): ApiService {
        return ApiService(httpClient)
    }

    @Provides
    fun getFriendlyRepository(friendlyRepositoryImpl: FriendlyRepositoryImpl): FriendlyRepository = friendlyRepositoryImpl
}