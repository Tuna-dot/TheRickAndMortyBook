package com.example.therickandmortybook.ui.screens.app.main.location.locationDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import com.example.therickandmortybook.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationDetailScreen (
    viewModel: LocationDetailViewModel = getViewModel(),
    locationId: Int
) {
    val location = viewModel.state.collectAsState()
    LaunchedEffect(locationId) {
        viewModel.getLocationById(locationId)
    }
    val characters = viewModel.characters.collectAsState()
    Column {
        when (val result = location.value) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(16.dp),
                    )
                }
            }
            is UiState.Success -> {
                DetailProfile(location = result.data, characters = characters.value)
            }
            is UiState.Error -> {
                Text(text = "Error: ${result.error.message}")
            }else -> {
                Text(text = "Unknown state")
            }
        }
    }
}
@Composable
fun DetailProfile(location: ResultDta, characters: List<ResultDto>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${location.name}")
        Text(text = "Type: ${location.type}")
        Text(text = "Dimension: ${location.dimension}")
        Text(text = "Created: ${location.created}")

        LazyColumn {
            items(characters) { character ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = character.name ?: "Unknown")
                        Text(text = character.species ?: "Unknown species")
                    }
                }
            }
        }
    }
}