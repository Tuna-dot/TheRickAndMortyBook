package com.example.therickandmortybook.ui.navigate

sealed class NavigateScreen(val route: String) {
    object CharacterList : NavigateScreen("character_list")
    object CharacterDetail : NavigateScreen("character_detail/{characterId}") {
        fun createRoute(characterId: Int): String = "character_detail/$characterId"
    }
}