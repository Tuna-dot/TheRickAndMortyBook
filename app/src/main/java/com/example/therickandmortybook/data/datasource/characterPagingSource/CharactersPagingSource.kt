package com.example.therickandmortybook.data.datasource.characterPagingSource

import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.map.toDataModel
import com.example.therickandmortybook.data.model.charcter.ResultDto
import kotlinx.coroutines.flow.first
import retrofit2.Response

class CharactersPagingSource(
    private val apiService: ApiService,
    private val dao: NoteDao
) : BasicPagingSource<DataModel>(
    loadData = { page ->
        val response = apiService.getCharacters(page)

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!
            val favorites = dao.getFavorites().first().map { it.id }

            val resultWithFavorites: List<DataModel> = body.results?.map { dto ->
                // Добавляем флаг избранного
                val dtoWithFavorite = dto.copy(isFavorite = favorites.contains(dto.id))
                dtoWithFavorite.toDataModel()
            } ?: emptyList()

            Response.success(resultWithFavorites)
        } else {
            Response.error<List<DataModel>>(response.code(), response.errorBody()!!)
        }
    }
)
