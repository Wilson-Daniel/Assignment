package com.example.mycomposeapplication.data.model

import com.squareup.moshi.Json


data class PokemonDetail(
    val id: Int,
    val name: String,
    @Json(name = "base_experience") val baseExperience: Int,
    val height: Int,
    @Json(name = "is_default") val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val sprites: Sprites,
    val types: List<Type>
)

data class Ability(
    val ability: AbilityInfo,
    @Json(name = "is_hidden") val isHidden: Boolean,
    val slot: Int
)

data class AbilityInfo(
    val name: String,
    val url: String
)

data class Sprites(
    @Json(name = "front_default") val frontDefault: String
)

data class Type(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)

