package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = getViewModel(),
    onItemClick: (Int) -> Unit
) {
    val pagingItems = viewModel.characterListFlow.collectAsLazyPagingItems()

    val searchQuery = viewModel.characterFilterStateFlow.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {

        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                viewModel.updateFilter(name = query)
            },
        )
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(pagingItems.itemCount) { index ->
            val character = pagingItems[index]
            character?.let {
                CharacterItem(
                    character = it,
                    onItemClick = { id -> id?.let(onItemClick) },
                    onFavoriteClick = { dataModel ->
                        viewModel.onFavoriteClick(dataModel)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        pagingItems.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = (loadState.refresh as LoadState.Error).error.localizedMessage
                                ?: "Ошибка",
                            onRetry = { retry() }
                        )
                    }
                }

                else -> Unit
            }

            if (loadState.append is LoadState.Loading) {
                item { LoadingItem() }
            }
        }
    }
}


@Composable
fun CharacterItem(
    character: DataModel,
    onItemClick: (Int?) -> Unit,
    onFavoriteClick: (DataModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(character.id) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = character.name ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = character.status ?: "",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(
                    onClick = { onFavoriteClick(character) }
                ) {
                    Icon(
                        imageVector = if (character.isFavorite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Избранное",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}


@Composable
fun LoadingItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = Color.Red)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: CharacterViewModel.FilterState,
    onSearchQueryChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = searchQuery.name,
            onValueChange = onSearchQueryChange,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}