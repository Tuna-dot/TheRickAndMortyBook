package com.example.therickandmortybook.data.datasource.characterPagingSource

import androidx.paging.PagingData
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.model.charcter.CharacterDto
import com.example.therickandmortybook.data.model.charcter.ResultDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CharactersPagingSource(
    private val apiService: ApiService,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val type: String?,
    private val gender: String?
) : BasicPagingSource<ResultDto>(
    loadData = { page ->

        val response = apiService.getCharacters(
            page = page,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender
        )
        if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!
            Response.success(body.results ?: emptyList())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }
)