package com.example.therickandmortybook.data.repository.episode.episodeById

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.episodes.ResultDte
import com.example.therickandmortybook.data.repository.basic.BasicRepository
import com.example.therickandmortybook.utils.UiState

class EpisodeByIdRepository(
    private val apiService: ApiService
): BasicRepository() {
    suspend fun getEpisodeById(episodeId: Int): UiState<ResultDte> {
        return safeApiCall { apiService.getEpisodeById(episodeId) }
    }
}