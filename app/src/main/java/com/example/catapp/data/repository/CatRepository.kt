package com.example.catapp.data.repository

import com.example.catapp.core.NetworkResult
import com.example.catapp.data.api.CatApiService
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catApiService: CatApiService // Corrected naming
) {

    // Fetch cat images from the API
    suspend fun getCats(apiKey: String): NetworkResult<List<String>> {
        return try {
            val response = catApiService.getCatImages(apiKey = apiKey)
            if (response.isSuccessful && response.body() != null) {
                val imageUrls = response.body()!!.map { it.url }
                NetworkResult.Success(imageUrls)
            } else {
                NetworkResult.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Failed to load cats: ${e.message}")
        }
    }
}
