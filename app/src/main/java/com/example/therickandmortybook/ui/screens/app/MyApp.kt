package com.example.therickandmortybook.ui.screens.app

import androidx.compose.runtime.Composable
import com.example.therickandmortybook.ui.screens.app.main.MainScreen
import com.example.therickandmortybook.ui.theme.TheRickAndMortyBookTheme

@Composable
fun MyApp() {
    TheRickAndMortyBookTheme {
        MainScreen()
    }
}