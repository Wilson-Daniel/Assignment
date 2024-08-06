
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.ui.PokemonListScreen
import com.example.mycomposeapplication.viewmodel.PokemonViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            val viewModel: PokemonViewModel = viewModel()
            PokemonListScreen(viewModel) { id ->
                navController.navigate("pokemon_detail/$id")
            }
        }
        composable("pokemon_detail/{id}") { backStackEntry ->
            val viewModel: PokemonViewModel = viewModel()
            val pokemonId = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            PokemonDetailScreen(viewModel, pokemonId)
        }
    }
}
