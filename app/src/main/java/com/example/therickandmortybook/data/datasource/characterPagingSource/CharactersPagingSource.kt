package com.example.therickandmortybook.data.datasource.characterPagingSource

import android.util.Log
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.map.toDataModel
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class CharactersPagingSource(
    private val apiService: ApiService,
    private val dao: NoteDao
) : BasicPagingSource<DataModel>(
    loadData = { page ->
        val response = apiService.getCharacters(page)
        if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!

            // Загружаем избранные id заранее
            val favoritesIds = dao.getFavoritesOnce()

            val resultWithFavorites: List<DataModel> = body.results?.map { dto ->
                val isFavorite = favoritesIds.contains(dto.id)
                dto.copy(isFavorite = isFavorite).toDataModel()
            } ?: emptyList()

            Response.success(resultWithFavorites)
        } else {
            // Лучше добавить проверку на null errorBody()
            val errorBody = response.errorBody()?.string() ?: "Неизвестная ошибка"
            Response.error<List<DataModel>>(response.code(), errorBody.toResponseBody())
        }
    }
)
