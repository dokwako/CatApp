package com.example.catapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.catapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {
    private val _cats = mutableStateOf<List<String>>(NetworkResult.Loading())
    val cats: State<NetworkResult<List<String>>> get()= _cats
}