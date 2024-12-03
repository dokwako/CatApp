package com.example.catapp.data.repository

import android.net.wifi.WifiManager.AddNetworkResult
import com.example.catapp.core.NetworkResult
import com.example.catapp.data.api.CatApiService
import okhttp3.Response
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val CatApiService: CatApiService
) {

    //fetch cat images from the Api
    suspend fun getCats(): NetworkResult<List<String>> {
        return try {
            val response = CatApiService.getCatImages()
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