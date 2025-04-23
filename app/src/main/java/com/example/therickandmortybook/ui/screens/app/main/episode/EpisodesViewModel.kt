package com.example.therickandmortybook.ui.screens.app.main.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.repository.episode.EpisodesRepository

class EpisodesViewModel(
    private val episodesRepository: EpisodesRepository
):ViewModel() {
    val episodeFlow = episodesRepository.getEpisode()
        .cachedIn(viewModelScope)
}