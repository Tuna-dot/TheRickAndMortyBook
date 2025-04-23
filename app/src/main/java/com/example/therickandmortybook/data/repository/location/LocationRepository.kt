package com.example.therickandmortybook.data.repository.location

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.locationPagingSource.LocationPagingSource
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val apiService: ApiService
) {
    fun getLocation(): Flow<PagingData<ResultDta>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { LocationPagingSource(apiService) }
        ).flow
    }
}