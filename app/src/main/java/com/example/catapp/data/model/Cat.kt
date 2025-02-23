package com.example.catapp.data.model

data class Cat(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    companion object {
        
        fun fromCatImage(catImage: CatImage): Cat {
            return Cat(
                id = catImage.id,
                name = catImage.breeds?.firstOrNull()?.name ?: "Unknown",
                imageUrl = catImage.url

                // what about this other one
            )
        }
    }
}
