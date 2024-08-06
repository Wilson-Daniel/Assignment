import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mycomposeapplication.data.model.Ability
import com.example.mycomposeapplication.data.model.PokemonDetail
import com.example.mycomposeapplication.data.model.Type
import com.example.mycomposeapplication.viewmodel.PokemonViewModel

// Define custom colors
val DeepBlue = Color(0xFF1E3A8A)
val LightBlue = Color(0xFF3B82F6)
val Gray = Color(0xFF6B7280)
val LightGray = Color(0xFFF3F4F6)
val White = Color(0xFFFFFFFF)

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel, pokemonId: Int) {
    viewModel.fetchPokemonDetail(pokemonId)
    val pokemonDetail by viewModel.pokemonDetail.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightGray
    ) {
        pokemonDetail?.let { detail ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                PokemonHeader(detail.name, detail.sprites.frontDefault)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonStats(detail)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonAbilities(detail.abilities)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonTypes(detail.types)
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = DeepBlue)
            }
        }
    }
}

@Composable
fun PokemonHeader(name: String, imageUrl: String) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = DeepBlue
            )
        }
    }
}

@Composable
fun PokemonStats(detail: PokemonDetail) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Pokemon Stats",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = DeepBlue
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem("ID", detail.id.toString())
                StatItem("Base Exp", detail.baseExperience.toString())
                StatItem("Height", "${detail.height / 10.0}m")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem("Weight", "${detail.weight / 10.0}kg")
                StatItem("Order", detail.order.toString())
                StatItem("Default", if (detail.isDefault) "Yes" else "No")
            }
        }
    }
}

@Composable
fun PokemonAbilities(abilities: List<Ability>) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Abilities",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = DeepBlue
            )
            Spacer(modifier = Modifier.height(12.dp))
            abilities.forEach { ability ->
                AbilityItem(ability)
                if (ability != abilities.last()) Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AbilityItem(ability: Ability) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = ability.ability.name, style = MaterialTheme.typography.body1, fontWeight = FontWeight.Medium, color = DeepBlue)
        if (ability.isHidden) {
            Text(
                text = "Hidden",
                style = MaterialTheme.typography.caption,
                color = White,
                modifier = Modifier
                    .background(LightBlue, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

@Composable
fun PokemonTypes(types: List<Type>) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Types",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = DeepBlue
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                types.forEach { type ->
                    TypeItem(type)
                }
            }
        }
    }
}

@Composable
fun TypeItem(type: Type) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = DeepBlue
    ) {
        Text(
            text = type.type.name,
            style = MaterialTheme.typography.body2,
            color = White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(text = value, style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold, color = DeepBlue)
        Text(text = label, style = MaterialTheme.typography.caption, color = Gray)
    }
}