package com.example.therickandmortybook.ui.screens.app.main.episode.episodeDetail

import com.example.therickandmortybook.data.model.episodes.ResultDte
import com.example.therickandmortybook.data.repository.episode.episodeById.EpisodeByIdRepository
import com.example.therickandmortybook.ui.screens.app.BaseViewModel

class EpisodeDetailViewModel(
    private val episodeByIdRepository: EpisodeByIdRepository
) : BaseViewModel<ResultDte>() {

    fun getEpisodeById(episodeId: Int) {
        loadData {
            episodeByIdRepository.getEpisodeById(episodeId)
        }
    }
}