package com.example.therickandmortybook.ui.screens.listscreen.detaillistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.ui.viewmodel.MainViewModel
import com.example.therickandmortybook.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: MainViewModel = getViewModel(),
    title: MutableState<String>
) {
    val characterState = viewModel.characterState.collectAsState().value

    LaunchedEffect(characterId) {
        viewModel.getCharacterById(characterId)
    }

    when (characterState) {
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp))
        }

        is UiState.Success<ResultDto> -> {
            val character = characterState.data
            title.value = character.name.toString()

            HeroProfile(character)

            Spacer(modifier = Modifier.height(16.dp))
        }

        is UiState.Error -> {
            val errorMessage = characterState.error
            Text(text = "Error: $errorMessage", color = Color.Red)
        }

        else -> {
            Text(text = "Unknown state", color = Color.Red)
        }
    }
}
@Composable
fun HeroProfile(character: ResultDto){
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)){
        Row (modifier = Modifier.fillMaxWidth()){
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                    contentScale = ContentScale.Crop
            )
        }
        Text(text = "Name: ${character.name}", fontWeight = FontWeight.Bold,
            fontSize = 28.sp)
        Text(text = "Species: ${character.species}",
            fontSize = 22.sp,
            )
        Text(text = "Status: ${character.status}"
        , fontSize = 22.sp)
        Text(text = "Gender: ${character.gender}"
            , fontSize = 22.sp)
        Text(text = "Origin: ${character.origin?.name}"
            , fontSize = 22.sp)
        Text(text = "Location: ${character.location?.name}"
            , fontSize = 22.sp)
        Text(text = "Created: ${character.created}"
            , fontSize = 22.sp)
    }
}