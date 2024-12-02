package com.example.catapp.ui.screens

import android.net.Network
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.catapp.ui.viewmodel.CatViewModel

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    val catsState = catViewModel.cats.collectAsState()

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (val result = catsState.value) {
            is NetworkResult.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResult.Success -> {
                result.data?.ForEach { catImageUrl ->
                    Image(
                        painter = rememberImagePainter(catImageUrl),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            is NetworkResult.Error -> {
                Text(text = "Error: ${result.message}")
            }
        }

    }
}