package com.example.catapp.ui.presentation

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Nairobi ,Kenya" ,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface

        )
    )

}

@Composable
fun CatItem(cat:Cat) {
    Row (
        modifier =Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(cat.imageUrl),
            contentDescription = "Cat Image",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = cat.name,
                style =MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Adopt me",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

    }
}

@Composable
fun CatGrid(catViewModel: CatViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2-column grid
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(catViewModel.catList) { cat ->
            CatGridItem(cat)
        }
    }
}

@Composable
fun CatGridItem(cat: Cat) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(cat.imageUrl),
            contentDescription = "Cat Image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = cat.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
