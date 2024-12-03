package com.example.catapp.data.api

import com.example.catapp.data.model.CatImage
import retrofit2.Response
import retrofit2.http.GET

interface CatApiService {
    @GET("path/to/endpoint")
    suspend fun getCatImages(): Response<List<CatImage>>
}
