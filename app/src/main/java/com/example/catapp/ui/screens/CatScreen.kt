package com.example.catapp.ui.screens



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.catapp.ui.viewmodel.CatViewModel
import coil.compose.AsyncImage
import com.example.catapp.core.NetworkResult


@Composable
fun CatScreen(catViewModel: CatViewModel) {
    val catsState = catViewModel.cats

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (val result = catsState.value) {
            is NetworkResult.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResult.Success -> {
                result.data?.forEach { catImageUrl ->
                    AsyncImage(
                        model = catImageUrl,
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