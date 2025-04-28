package com.example.therickandmortybook.ui.screens.app.main.favorites

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = getViewModel(),
    navController: NavController // Для перехода на детальный экран
) {
    // Состояние избранных персонажей
    val favorites = viewModel.favorites.collectAsState(emptyList())

    // Логируем только изменения в списке
    LaunchedEffect(favorites.value) {
        Log.d("FavoriteScreen", "Favorites loaded: ${favorites.value.size}")
    }

    // Если нет избранных персонажей, показываем соответствующее сообщение
    if (favorites.value.isEmpty()) {
        Log.d("FavoriteScreen", "Favorites are empty!")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Проверяем, если нет избранных персонажей
        if (favorites.value.isEmpty()) {
            Text("Нет избранных персонажей", fontSize = 18.sp, color = Color.Gray)
        } else {
            favorites.value.forEach { character ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                // Переход на детальный экран
                                navController.navigate("characterDetail/${character.id}")
                            }
                    ) {
                        AsyncImage(
                            model = character.image,
                            contentDescription = null,
                            modifier = Modifier
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

                        // Иконка "Удалить из избранного"
                        IconButton(
                            onClick = {
                                viewModel.removeFromFavorites(character.id)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Удалить из избранного",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}
