package com.example.therickandmortybook.ui.screens.app.main.episode.episodeDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therickandmortybook.data.model.episodes.ResultDte
import com.example.therickandmortybook.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeDetailScreen(
    viewModel: EpisodeDetailViewModel = getViewModel(),
    episodeId: Int
) {
    val episode = viewModel.state.collectAsState()
    LaunchedEffect(episodeId) {
        viewModel.getEpisodeById(episodeId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (val result = episode.value) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(16.dp),
                    )
                }
            }

            is UiState.Success -> {
                DetailProfile(episode = result.data)
            }

            is UiState.Error -> {
                Text(text = "Error: ${result.error.message}")
            }

            else -> {
                Text(text = "Unknown state")
            }
        }
    }
}

@Composable
fun DetailProfile(episode: ResultDte) {
    Column {

        Text(
            text = "Name: ${episode.name}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Episode: ${episode.episode}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Air Date: ${episode.airDate}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Created: ${episode.created}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}