package com.sargis.khlopuzyan.pixabayimagesearch.di

import com.sargis.khlopuzyan.pixabayimagesearch.network.ApiService
import com.sargis.khlopuzyan.pixabayimagesearch.ui.components.MainRepository
import com.sargis.khlopuzyan.pixabayimagesearch.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }
}