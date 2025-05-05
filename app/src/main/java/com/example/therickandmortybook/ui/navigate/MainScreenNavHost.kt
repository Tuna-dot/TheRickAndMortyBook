package com.example.therickandmortybook.ui.navigate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.therickandmortybook.ui.screens.app.main.character.CharacterScreen
import com.example.therickandmortybook.ui.screens.app.main.character.characterDetail.DetailScreen
import com.example.therickandmortybook.ui.screens.app.main.episode.EpisodesScreen
import com.example.therickandmortybook.ui.screens.app.main.episode.episodeDetail.EpisodeDetailScreen
import com.example.therickandmortybook.ui.screens.app.main.favorites.FavoriteScreen
import com.example.therickandmortybook.ui.screens.app.main.location.LocationScreen
import com.example.therickandmortybook.ui.screens.app.main.location.locationDetail.LocationDetailScreen

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigateScreen.Character.route,
        modifier = modifier
    ) {
        composable(NavigateScreen.Character.route) {
            CharacterScreen(
                onItemClick = { id ->
                    navController.navigate(NavigateScreen.CharacterDetail.createRoute(id))
                    Log.e("ololo", "MainScreenNavHost: navigate")
                }
            )
        }
        composable(
            route = NavigateScreen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            DetailScreen(characterId = characterId)

        }
        composable(NavigateScreen.Location.route) {
            LocationScreen(
                onItemClick = { id ->
                    navController.navigate(NavigateScreen.LocationDetail.createRoute(id!!))
                }
            )
        }
        composable(
            route = NavigateScreen.LocationDetail.route,
            arguments = listOf(navArgument("locationId") { type = NavType.IntType })
        ) { backStackEntry ->
            val locationId = backStackEntry.arguments?.getInt("locationId") ?: return@composable
            LocationDetailScreen(locationId = locationId)
        }
        composable(NavigateScreen.Episode.route) {
            EpisodesScreen(
                onItemClick = { id ->
                    navController.navigate(NavigateScreen.EpisodeDetail.createRoute(id!!))
                }
            )
        }
        composable(
            route = NavigateScreen.EpisodeDetail.route,
            arguments = listOf(navArgument("episodeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getInt("episodeId") ?: return@composable
            EpisodeDetailScreen(episodeId = episodeId)
        }
        composable(NavigateScreen.Favorite.route) {
            FavoriteScreen(navController = navController)
        }
    }
}