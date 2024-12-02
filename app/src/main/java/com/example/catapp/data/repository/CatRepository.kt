package com.example.catapp.data.repository

import android.net.wifi.WifiManager.AddNetworkResult
import okhttp3.Response
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val CatApiService: CatApiService
) {

    //fetch cat images from the Api
    suspend fun getCats(): NetworkResult<List<String>> {
        return try {
            val response = CatApiService.getCats()
            if (response.isSuccessful) {
                response.body()?.let { NetworkResult.Success(it)}
                 ?: NetworkResult.Error("No data found")
            }  else {
                NetworkResult.Error("Error: ${response.message()}")
            }

        } catch (e: Exception) {
            NetworkResult.Error("Network Error: ${e.localizedmessage}")
        }

    }
}