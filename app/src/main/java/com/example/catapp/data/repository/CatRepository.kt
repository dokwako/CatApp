package com.example.catapp.data.repository

import com.example.catapp.core.NetworkResult
import com.example.catapp.data.api.CatApiService
import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catApiService: CatApiService // Corrected naming
) {

    // Fetch cat images from the API
    suspend fun getCats(apiKey: String): NetworkResult<List<Cat>> {
        return try {
            val response = catApiService.getCatImages(apiKey = apiKey)
            if (response.isSuccessful && response.body() != null) {
                val catList = response.body()!!.map { catImage ->
                    Cat (
                        id = catImage.id,
                        name = catImage.breeds?.firstOrNull()?.name ?: "Unknown",
                        imageUrl = catImage.url
                    )
                }
                NetworkResult.Success(catList)
            } else {
                NetworkResult.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Failed to load cats: ${e.message}")
        }
    }
}
