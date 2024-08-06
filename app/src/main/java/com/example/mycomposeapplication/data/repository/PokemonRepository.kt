package com.example.mycomposeapplication.data.repository

import com.example.mycomposeapplication.data.model.PokemonDetail
import com.example.mycomposeapplication.data.model.PokemonList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail
}

object PokemonApi {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}

class PokemonRepository {
    private val apiService = PokemonApi.service

    suspend fun getPokemonList(): PokemonList {
        return apiService.getPokemonList()
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return apiService.getPokemonDetail(id)
    }
}
