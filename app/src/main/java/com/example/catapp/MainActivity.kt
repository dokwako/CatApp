package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.catapp.core.NetworkResult
import com.example.catapp.ui.theme.CatAppTheme
import com.example.catapp.ui.viewmodel.CatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatAppTheme {
                WelcomeScreen(viewModel = viewModel())
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Text(
//                        text = "Welcome to Cat App!",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//
//                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(viewModel: CatViewModel) {
    // Collecting the current state of the cat images
    val catsState = viewModel.cats.value

    // Fetch cats on initial launch
    LaunchedEffect(Unit) {
        viewModel.fetchCats()
    }

    // Display the UI based on the current state
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (catsState) {
            is NetworkResult.Success -> {
                // Show the list of cats when the data is successfully fetched
                CatList(cats = catsState.data)
            }
            is NetworkResult.Loading -> {
                // Show loading indicator while waiting for data
                CircularProgressIndicator()
            }
            is NetworkResult.Error -> {
                // Show error message if fetching failed
                Text(text = catsState.message)
            }
        }
    }
}

@Composable
fun CatList(cats: List<String>) {
    LazyColumn {
        items(cats) { cat ->
            AsyncImage(
                model = cat, // URL of the cat image
                contentDescription = "Cat Image",
                modifier = Modifier.fillMaxWidth() // You can modify this based on your layout needs
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CatAppTheme {
        WelcomeScreen(viewModel = viewModel())
    }
}

