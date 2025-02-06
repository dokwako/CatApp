package com.example.catapp.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catapp.R
import com.example.catapp.data.api.CatApiService
import com.example.catapp.data.repository.CatRepository
import com.example.catapp.ui.viewmodel.CatViewModel

@Composable
fun CatScreen(catViewModel: CatViewModel) {
    Scaffold(
        topBar = {TopBar() },
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopBar()
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

