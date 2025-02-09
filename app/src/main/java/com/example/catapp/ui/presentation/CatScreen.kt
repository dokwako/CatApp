package com.example.catapp.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.catapp.R
import com.example.catapp.data.api.CatApiService
import com.example.catapp.data.model.Cat
import com.example.catapp.data.repository.CatRepository
import com.example.catapp.ui.viewmodel.CatViewModel

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        catViewModel.fetchCats()
    }
    Scaffold(
        topBar = {TopBar() },
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(searchText, onSearchTextChange =  { searchText = it})
        }

        LazyColumn(
            modifier =Modifier.fillMaxSize()
        ) {
            items(catViewModel.catList) {cat ->
                CatItem(cat)
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
        Text(text = "Nairobi ,Kenya" , style = MaterialTheme.typography.titleMedium)
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        label = { Text("Search for Pets") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        }
    )

}

@Composable
fun CatItem(Cat:Cat) {
    Row (
        modifier =Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = rememberAsyncImagePainter(cat.imageUrl),
            contentDescription = "Cat Image",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = cat.name,
            modifier = Modifier.padding(8.dp),
            style =MaterialTheme.typography.bodyMedium
        )
    }
}
