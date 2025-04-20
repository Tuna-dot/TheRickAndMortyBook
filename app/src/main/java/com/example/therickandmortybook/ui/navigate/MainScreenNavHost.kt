package com.example.therickandmortybook.ui.navigate

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.therickandmortybook.ui.screens.listscreen.CharacterListScreen
import com.example.therickandmortybook.ui.screens.listscreen.detaillistscreen.CharacterDetailScreen
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreenNavHost(
    title: MutableState<String>,
    onBackClick: MutableState<(() -> Unit)?>,

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigateScreen.CharacterList.route
    ) {
        composable(NavigateScreen.CharacterList.route) {
            CharacterListScreen(
                navController = navController,
                viewModel = getViewModel(),
                title = title
            )
            onBackClick.value = null
        }
        composable(NavigateScreen.CharacterDetail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            characterId?.let {
                title.value = "Character Detail"
                onBackClick.value = { navController.popBackStack() }
                CharacterDetailScreen(
                    characterId,
                    title = title
                )
            }
        }
    }
}