package com.example.therickandmortybook.ui.screens.app.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.therickandmortybook.ui.bar.bottombar.BottomBar
import com.example.therickandmortybook.ui.navigate.MainScreenNavHost
import com.example.therickandmortybook.ui.bar.topbar.CenteredTopBar
import com.example.therickandmortybook.ui.navigate.NavigateScreen

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen() {

    val title = remember { mutableStateOf("Rick and Morty") }
    val navController = rememberNavController()
    val currentBackStack = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStack?.destination?.route
    val showBottomBar = currentRoute in listOf(
        NavigateScreen.Character.route,
        NavigateScreen.Location.route,
        NavigateScreen.Episode.route
    )
    Scaffold(
        topBar = {
            CenteredTopBar(
                title = title.value,
                onBackClick = { navController.popBackStack() },
                currentRoute = currentRoute
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController = navController, currentRoute = currentRoute)
            }
        }
    ) { padding ->
        MainScreenNavHost(navController, modifier = Modifier.padding(padding))
    }
}