package com.example.therickandmortybook.data.datasource.episodesPagingSource

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.model.episodes.ResultDte
import retrofit2.Response

class EpisodesPagingSource(
    private val apiService: ApiService
) : BasicPagingSource<ResultDte>(
    loadData = { page ->
        val response = apiService.getEpisodes(page)
        if (response.isSuccessful &&
            response.body() != null
        ) {
            val body = response.body()!!
            Response.success(body.results ?: emptyList())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }
)