package com.example.therickandmortybook.data.repository.episode

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.episodesPagingSource.EpisodesPagingSource
import com.example.therickandmortybook.data.model.episodes.ResultDte
import kotlinx.coroutines.flow.Flow

class EpisodesRepository(
    private val apiService: ApiService
) {
    fun getEpisode(): Flow<PagingData<ResultDte>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { EpisodesPagingSource(apiService) }
        ).flow
    }
}