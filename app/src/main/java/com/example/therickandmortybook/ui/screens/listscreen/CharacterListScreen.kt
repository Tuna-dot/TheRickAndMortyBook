package com.example.therickandmortybook.ui.screens.listscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.ui.navigate.NavigateScreen
import com.example.therickandmortybook.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel(),
    title: MutableState<String>
) {
    val characters = viewModel.charactersPaging.collectAsLazyPagingItems()

    val season = remember {
        listOf(
            "Season 1", "Season 2", "Season 3", "Season 4", "Season 5",
            "Season 6", "Season 7", "Season 8"
        )
    }
    var selectedSeason by remember { mutableStateOf("") }

    var searchQuery by remember { mutableStateOf(viewModel.searchQuery) }

    val filteredItems = remember(searchQuery, characters) {
        derivedStateOf {
            characters.itemSnapshotList.items.filter { result ->
                result.name?.contains(searchQuery, ignoreCase = true) == true ||
                        result.species?.contains(searchQuery, ignoreCase = true) == true||
                        result.episode?.contains(searchQuery, ) == true||
                        result.location?.name.toString().contains(searchQuery, ignoreCase = true) == true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        CategoryList(
            season = season,
            selectedSeason = selectedSeason,
            onCategorySelected = { selected ->
                selectedSeason = selected
                title.value = selected }
        )
        SearchField(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it }
        )
        Spacer(modifier = Modifier.width(10.dp))
        RickAndMortyList(characters = characters, filteredItems = filteredItems.value
        , navController = navController)
    }
}


@Composable
fun SearchField(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        label = { Text("Поиск") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun CategoryList(
    season: List<String>,
    selectedSeason: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow {
        items(season.size) { index ->
            var category = season[index]
            val isSelected = category == selectedSeason
            Card(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 5.dp)
                    .clickable { onCategorySelected(season[index]) },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

                )
            ) {
                Text(
                    text = category,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun RickAndMortyList(
    characters: LazyPagingItems<ResultDto>, filteredItems: List<ResultDto>
    , navController: NavController
) {
    LazyColumn(modifier = Modifier.fillMaxSize()
    ) {
        items(filteredItems.size) { index ->
            val character = filteredItems[index]
            CharactersCard(result = character, navController = navController)
        }

        characters.apply {
            if (loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
            else if (loadState.refresh is LoadState.Error) {
                val error = loadState.refresh as LoadState.Error
                item {
                    Text(
                        text = error.error.message ?: "Unknown error",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CharactersCard(result: ResultDto?,
                   navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            navController.navigate(NavigateScreen.CharacterDetail.createRoute(characterId = result?.id ?: 0))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = result?.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = result?.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "${result?.species ?: "Unknown"} - ${result?.status ?: "?"}",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Gender: ${result?.gender ?: "Unknown"}",
                fontSize = 12.sp,
                color = Color.Gray)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = result?.location?.name.toString(),
                fontSize = 12.sp,
                color = Color.Gray)
        }

    }
}