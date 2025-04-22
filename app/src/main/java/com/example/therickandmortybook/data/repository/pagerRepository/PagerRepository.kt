package com.example.therickandmortybook.data.repository.pagerRepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.data.datasource.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow

class PagerRepository(
    private val apiService: ApiService
) {
     fun getCharacters(): Flow<PagingData<ResultDto>>{
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { CharactersPagingSource(apiService) }
        ).flow
    }
}