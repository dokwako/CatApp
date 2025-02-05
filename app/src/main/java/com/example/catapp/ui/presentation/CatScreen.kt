package com.example.catapp.ui.presentation

import android.graphics.drawable.Icon
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import coil.compose.AsyncImage
import com.example.catapp.ui.viewmodel.CatViewModel
import com.example.catapp.core.NetworkResult
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.catapp.data.api.CatApiService
import com.example.catapp.data.repository.CatRepository
import com.google.accompanist.swiperefresh.SwipeRefresh

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    val catsState = catViewModel.cats

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }

    Scaffold(
        topBar = {TopBar() },
        bottomBar = {bottomNavigationBar() }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar()
            PetCategoryTabs()

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
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement
                            .spacedBy(16.dp),
                        horizontalArrangement = Arrangement
                            .spacedBy(16.dp)
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
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = " Nairobi, Kenya ", style = MaterialTheme.typography.titleMedium)
        Image(painter = painterResource(id = R.drawable.Profile), contentDescription = "Profile")
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text("Search for pets") },
        leadingIcon = { Icon (Icons.Default.Search, contentDescription = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun PetCategorySelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        listOf("Dogs", "Cats", "Rabbits").forEach { category ->
            Button(
                onClick = { /* Handle category selection */ },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(category)
            }
        }
    }
}

@Composable
fun CatList(cats: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cats) { cat ->
            AsyncImage(
                model = cat,
                contentDescription = "Cat Image" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CatScreenPreview() {
    CatScreen(catViewModel = CatViewModel(CatRepository(CatApiService)))
}

@Composable
fun CatCard(catImageUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Ensures cards are square
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
        )
    )
    {
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
