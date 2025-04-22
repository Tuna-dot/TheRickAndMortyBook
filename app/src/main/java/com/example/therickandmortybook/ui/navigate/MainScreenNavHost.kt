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
import com.example.therickandmortybook.ui.screens.app.main.character.detail.DetailScreen

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
                    navController.navigate(NavigateScreen.Detail.createRoute(id))
                    Log.e("ololo", "MainScreenNavHost: navigate", )
                }
            )
        }
        composable(
            route = NavigateScreen.Detail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
                DetailScreen(characterId = characterId,)

        }
    }
}