package com.example.mycomposeapplication.data.remote


import com.example.mycomposeapplication.data.model.PokemonDetail
import com.example.mycomposeapplication.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail
}
