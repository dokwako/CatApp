package com.example.catapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.catapp.ui.viewmodel.CatViewModel

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    val catsState = catViewModel.cats.collectAsState()

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }
}