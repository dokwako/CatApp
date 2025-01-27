package com.example.catapp.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.catapp.ui.viewmodel.CatViewModel
import com.example.catapp.core.NetworkResult
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    val catsState = catViewModel.cats

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }

    when (val result = catsState.value) {
        is NetworkResult.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is NetworkResult.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns in the grid
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(result.data) { catImageUrl ->
                    CatCard(catImageUrl)
                }
            }
        }
        is NetworkResult.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Error: ${result.message}")
            }
        }
    }
}

@Composable
fun CatCard(catImageUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Ensures cards are square
            .padding(8.dp), // Add some padding to verify space
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp) // Internal padding
        ) {
            AsyncImage(
                model = catImageUrl,
                contentDescription = "Cat Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


