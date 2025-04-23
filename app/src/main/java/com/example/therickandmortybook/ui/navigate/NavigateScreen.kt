package com.example.therickandmortybook.ui.navigate

sealed class NavigateScreen(val route: String) {
    object Character : NavigateScreen("character")
    object Detail : NavigateScreen("characterDetail/{characterId}") {
        fun createRoute(characterId: Int): String = "characterDetail/$characterId"
    }

    object Location : NavigateScreen("location")
    object DetailLocation : NavigateScreen("detailLocation/{locationId}") {
        fun createRoute(locationId: Int): String = "detailLocation/$locationId"
    }

    object Episode : NavigateScreen("episode")
    object DetailEpisode : NavigateScreen("detailEpisode/{episodeId}") {
        fun createRoute(episodeId: Int): String = "detailEpisode/$episodeId"
    }
}