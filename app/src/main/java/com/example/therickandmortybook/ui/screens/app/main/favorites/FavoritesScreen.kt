package com.example.therickandmortybook.ui.screens.app.main.favorites

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.therickandmortybook.ui.screens.app.main.character.CharacterItem
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = getViewModel(),
    navController: NavController
) {
    val favorites = viewModel.favoritesFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(favorites.itemCount) { index ->
            val character = favorites[index]
            character?.isFavorite = true
            character?.let { dataModel ->
                CharacterItem(
                    character = dataModel,
                    onItemClick = {
                        id -> navController.navigate("characterDetail/$id")
                                  },
                    onFavoriteClick = {
                        model -> viewModel.removeFromFavorites(model)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        favorites.apply {
            when (loadState.refresh) {

                is androidx.paging.LoadState.Error -> {
                    item {
                        Text(
                            "Ошибка загрузки избранного",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}
