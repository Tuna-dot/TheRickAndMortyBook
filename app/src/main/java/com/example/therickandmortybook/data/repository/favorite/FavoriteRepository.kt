package com.example.therickandmortybook.data.repository.favorite

import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.Flow

class FavoriteRepository (
    private val dao: NoteDao
){
    fun getFavoriteCharacters() : Flow<List<DataModel>> = dao.getFavorites()

    suspend fun toggleFavorite(character: DataModel) {
        val current = dao.getCharacterById(character.id)
        val newFavorite = current?.isFavorite != true
        dao.insertFavorite(character.copy(isFavorite = newFavorite))
    }

    suspend fun removeFavorite(character: DataModel) {
        dao.removeFavorite(character)
    }

}