package com.example.mycomposeapplication.data.model


import com.squareup.moshi.Json


data class Pokemon(
    val name: String,
    @Json(name = "url") val detailsUrl: String
) {
    val id: Int
        get() = detailsUrl.split("/").dropLast(1).last().toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
