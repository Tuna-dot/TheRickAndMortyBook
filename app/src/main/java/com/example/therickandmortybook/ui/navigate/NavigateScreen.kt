package com.example.therickandmortybook.ui.navigate

sealed class NavigateScreen(val route: String) {
    object Character : NavigateScreen("character")
    object CharacterDetail : NavigateScreen("characterDetail/{characterId}") {
        fun createRoute(characterId: Int): String = "characterDetail/$characterId"
    }

    object Location : NavigateScreen("location")
    object LocationDetail : NavigateScreen("detailLocation/{locationId}") {
        fun createRoute(locationId: Int): String = "detailLocation/$locationId"
    }

    object Episode : NavigateScreen("episode")
    object EpisodeDetail : NavigateScreen("detailEpisode/{episodeId}") {
        fun createRoute(episodeId: Int): String = "detailEpisode/$episodeId"
    }

    object Favorite : NavigateScreen("favorite")
}