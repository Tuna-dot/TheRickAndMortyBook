package com.example.therickandmortybook.ui.serviceLocator

import com.example.therickandmortybook.ui.screens.app.main.character.CharacterViewModel
import com.example.therickandmortybook.ui.screens.app.main.character.characterDetail.CharacterDetailViewModel
import com.example.therickandmortybook.ui.screens.app.main.episode.EpisodesViewModel
import com.example.therickandmortybook.ui.screens.app.main.episode.episodeDetail.EpisodeDetailViewModel
import com.example.therickandmortybook.ui.screens.app.main.favorites.FavoriteViewModel
import com.example.therickandmortybook.ui.screens.app.main.location.LocationViewModel
import com.example.therickandmortybook.ui.screens.app.main.location.locationDetail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharacterViewModel(
            pagerRepository = get(),

            )
    }
    viewModel {
        CharacterDetailViewModel(
            characterRepository = get()
        )
    }
    viewModel {
        EpisodesViewModel(
            episodesRepository = get()
        )
    }
    viewModel {
        EpisodeDetailViewModel(
            episodeByIdRepository = get()
        )
    }
    viewModel {
        LocationDetailViewModel(
            locationByIdRepository = get(),
            charactersRepository = get()
        )
    }
    viewModel {
        LocationViewModel(
            locationRepository = get()
        )
    }
    viewModel {
        FavoriteViewModel(
            pagerRepository = get()
        )
    }
}