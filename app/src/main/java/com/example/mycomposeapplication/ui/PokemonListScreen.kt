package com.example.mycomposeapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mycomposeapplication.data.model.Pokemon
import com.example.mycomposeapplication.viewmodel.PokemonViewModel

import androidx.compose.ui.graphics.Color

// Define custom colors
val DeepBlue = Color(0xFF1E3A8A)
val LightBlue = Color(0xFF3B82F6)
val Gray = Color(0xFF6B7280)
val LightGray = Color(0xFFF3F4F6)
val White = Color(0xFFFFFFFF)

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, onPokemonClick: (Int) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val pokemonList by viewModel.pokemonList.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightGray
    ) {
        Column {
            // App Header with professional color
            TopAppBar(
                title = {
                    Text(
                        "Pokémon Serene",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                    )
                },
                backgroundColor = DeepBlue,
                elevation = 4.dp
            )

            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                modifier = Modifier.padding(16.dp)
            )

            pokemonList?.let { list ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(list.results.filter {
                        it.name.contains(searchQuery, ignoreCase = true)
                    }) { pokemon ->
                        PokemonListItem(pokemon = pokemon, onClick = { onPokemonClick(pokemon.id) })
                    }
                }
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = DeepBlue)
                }
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Search Pokémon", color = Gray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = DeepBlue) },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DeepBlue,
            unfocusedBorderColor = Gray,
            cursorColor = DeepBlue,
            textColor = DeepBlue,
            backgroundColor = White
        )
    )
}

@Composable
fun PokemonListItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = pokemon.imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = DeepBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ID: ${pokemon.id}",
                    style = MaterialTheme.typography.body2,
                    color = Gray
                )
            }
        }
    }
}