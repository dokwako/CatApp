package com.example.catapp.data.model

data class CatImage(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<Breed>? // Optional, based on TheCatAPI's response
)

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val life_span: String,
    val wikipedia_url: String?
)
