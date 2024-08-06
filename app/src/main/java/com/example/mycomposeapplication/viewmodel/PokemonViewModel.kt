package com.example.mycomposeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapplication.data.model.PokemonDetail
import com.example.mycomposeapplication.data.model.PokemonList
import com.example.mycomposeapplication.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _pokemonList = MutableLiveData<PokemonList>()
    val pokemonList: LiveData<PokemonList> = _pokemonList

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail> = _pokemonDetail

    fun fetchPokemonList() {
        viewModelScope.launch {
            val result = repository.getPokemonList()
            _pokemonList.postValue(result)
        }
    }

    fun fetchPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            val result = repository.getPokemonDetail(pokemonId)
            _pokemonDetail.postValue(result)
        }
    }
}
