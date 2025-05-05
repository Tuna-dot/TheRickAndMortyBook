package com.example.therickandmortybook.ui.screens.app.main.character.characterDetail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = getViewModel(),
) {
    val characterState = viewModel.state.collectAsState()

    LaunchedEffect(characterId) {
        Log.e("ololo", "DetailScreen: Fetching character with ID: $characterId")
        viewModel.getCharacterById(characterId)
    }

    when (val state = characterState.value) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp).padding(16.dp)
                )
            }
        }

        is UiState.Success -> {
            val character = state.data // Это правильное извлечение данных из UiState.Success
            Log.d("ololo", "Character data loaded: $character")
            HeroProfile(character = character)
        }

        is UiState.Error -> {
            Log.e("ololo", "Error loading character: ${state.error.message}")
            Text(text = "Error: ${state.error.message}", color = Color.Red)
        }

        else -> {
            Log.d("ololo", "Unknown state: $state")
            Text(text = "Unknown state", color = Color.Red)
        }
    }
}

@Composable
fun HeroProfile(character: ResultDto) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Name: ${character.name}", fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = "Species: ${character.species}",
            fontSize = 22.sp,
        )
        Text(
            text = "Status: ${character.status}", fontSize = 22.sp
        )
        Text(
            text = "Gender: ${character.gender}", fontSize = 22.sp
        )
        Text(
            text = "Origin: ${character.origin?.name}", fontSize = 22.sp
        )
        Text(
            text = "Location: ${character.location?.name}", fontSize = 22.sp
        )
        Text(
            text = "Created: ${character.created}", fontSize = 22.sp
        )
    }
}