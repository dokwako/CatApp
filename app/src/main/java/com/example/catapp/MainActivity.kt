package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.catapp.core.NetworkResult
import com.example.catapp.ui.theme.CatAppTheme
import com.example.catapp.ui.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatAppTheme {
                WelcomeScreen() // Start with WelcomeScreen composable
            }
        }
    }
}

//
@Composable
fun WelcomeScreen(viewModel: CatViewModel = hiltViewModel()) {
    val catsState = viewModel.cats.value

    // Trigger API fetch on first composition
    LaunchedEffect(Unit) {
        viewModel.fetchCats()
    }

    //

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            when (catsState) {
                is NetworkResult.Success -> {
                    CatList(
                        cats = catsState.data, // Pass the list of cats
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                is NetworkResult.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                is NetworkResult.Error -> Text(
                    text = catsState.message ?: "Unknown Error",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@Composable
fun CatList(cats: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(cats) { cat ->
            AsyncImage(
                model = cat, // The URL of the cat image
                contentDescription = "Cat Image",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
