package com.example.therickandmortybook.ui.screens.app.main.episode.episodeDetail

import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.episodes.ResultDte
import com.example.therickandmortybook.data.repository.episode.episodeById.EpisodeByIdRepository
import com.example.therickandmortybook.ui.screens.app.BaseViewModel
import com.example.therickandmortybook.utils.UiState

class EpisodeDetailViewModel(
    private val episodeByIdRepository: EpisodeByIdRepository
) : BaseViewModel<ResultDte>() {

    fun getEpisodeById(episodeId: Int) {
        loadData {
            val uiState = episodeByIdRepository.getEpisodeById(episodeId)
            if (uiState is UiState.Success) {
                uiState.data // Результат будет доступен здесь
            } else {
                throw Exception("Error fetching data")
            }
        }
    }
}