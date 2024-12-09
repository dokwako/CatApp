package com.example.catapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.core.NetworkResult
import com.example.catapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {
    private val _cats = mutableStateOf<NetworkResult<List<String>>>(NetworkResult.Loading)
    val cats: State<NetworkResult<List<String>>> get()= _cats

    //Fetch cat data
    fun fetchCats() {
        viewModelScope.launch {
            _cats.value = NetworkResult.Loading
            val result = catRepository.getCats("live_dMNF78WWpdNRXSPYgegR5C3wygiNgtgIBcoompnU6LeFnypryQ7YEhkzzMJjp8AG")
            _cats.value = result
        }

    }
}