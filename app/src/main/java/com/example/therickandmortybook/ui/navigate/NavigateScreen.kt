package com.example.therickandmortybook.ui.navigate

sealed class NavigateScreen(val route: String) {
    object Character : NavigateScreen("character")
    object Detail : NavigateScreen("detail/{characterId}") {
        fun createRoute(characterId: Int): String = "detail/$characterId"
    }
}