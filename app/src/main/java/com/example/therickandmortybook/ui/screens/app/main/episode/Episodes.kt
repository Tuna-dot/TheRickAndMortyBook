package com.example.therickandmortybook.ui.screens.app.main.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.therickandmortybook.data.model.episodes.ResultDte
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = getViewModel(),
    onItemClick: (Int?) -> Unit
) {
    val pagingItems = viewModel.episodeFlow.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        items(pagingItems.itemCount) { index ->
            val location = pagingItems[index]
            location?.let {
                EpisodeItem(
                    episode = it,
                    onItemClick = { id ->
                        id?.let { onItemClick(it) }
                    })
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EpisodeItem(
    episode: ResultDte?,
    onItemClick: (Int?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                episode?.id?.let { id ->
                    onItemClick(id)
                }
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Text(
                text = episode?.name ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = episode?.episode ?: "",
                fontSize = 16.sp
            )
            Text(
                text = episode?.airDate ?: "",
                fontSize = 16.sp,
            )

        }
    }
}