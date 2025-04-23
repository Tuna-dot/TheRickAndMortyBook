package com.example.therickandmortybook.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.characterPagingSource.CharactersPagingSource
import com.example.therickandmortybook.data.model.charcter.ResultDto
import kotlinx.coroutines.flow.Flow

class PagerRepository(
    private val apiService: ApiService
) {
     fun getCharacters(): Flow<PagingData<ResultDto>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { CharactersPagingSource(apiService) }
        ).flow
    }
}