package com.example.therickandmortybook.data.repository.location.locationById

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import com.example.therickandmortybook.data.repository.basic.BasicRepository
import com.example.therickandmortybook.utils.UiState

class LocationByIdRepository(
    private val apiService: ApiService
) : BasicRepository() {
    suspend fun getLocationById(episodeId: Int): UiState<ResultDta> {
        return safeApiCall { apiService.getLocationById(episodeId) }
    }
}