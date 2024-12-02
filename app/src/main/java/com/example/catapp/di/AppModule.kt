package com.example.catapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //provide Retrofit
    @Provides
    @Singleton

    fun provideRetrofit() :Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(OkHttpClient.Builder().build())
            .build()

    }

    // Provide CatApi for retrofit
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CatApiService {
        return retrofit.create(CatApiService::class.java)

    }

    // Provide CatRepository
    @Provides
    @Singleton
    fun provideCatRepository(apiService: CatApiService): CatRepository {
        return CatRepository(apiService)

    }
}