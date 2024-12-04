package com.example.catapp.data.api

import com.example.catapp.data.model.CatImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    suspend fun getCatImages(
        @Query("limit") limit: Int = 10, // Optional query parameter for image count
        @Query("api_key") apiKey: String // API key parameter
    ): Response<List<CatImage>>
}
