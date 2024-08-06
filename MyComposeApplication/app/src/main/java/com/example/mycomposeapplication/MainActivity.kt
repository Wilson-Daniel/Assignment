package com.example.mycomposeapplication

import PokemonDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.ui.PokemonListScreen
import com.example.mycomposeapplication.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: PokemonViewModel = viewModel()

            NavHost(navController = navController, startDestination = "pokemon_list") {
                composable("pokemon_list") {
                    PokemonListScreen(viewModel = viewModel) { pokemonId ->
                        navController.navigate("pokemon_detail/$pokemonId")
                    }
                }
                composable("pokemon_detail/{pokemonId}") { backStackEntry ->
                    val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toInt() ?: 0
                    PokemonDetailScreen(viewModel = viewModel, pokemonId = pokemonId)
                }
            }
        }
    }
}
