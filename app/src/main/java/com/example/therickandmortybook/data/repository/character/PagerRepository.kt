package com.example.therickandmortybook.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.characterPagingSource.CharactersPagingSource
import com.example.therickandmortybook.data.model.charcter.ResultDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PagerRepository(
    private val apiService: ApiService,
    private val dao: NoteDao,
    private val io: CoroutineDispatcher
) {
    fun getCharacters(): Flow<PagingData<DataModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5), // Размер страницы можно настроить
            pagingSourceFactory = { CharactersPagingSource(apiService, dao) }
        ).flow
    }
    suspend  fun getCharacterById(id : Int) : DataModel? {
        return withContext(io) {
            dao.getCharacterById(id)
        }
    }

    // Получаем избранных персонажей
    fun getFavorites(): Flow<List<DataModel>> {
        return dao.getFavorites()
    }

    // Добавление/удаление персонажа в избранное
    suspend fun toggleFavorite(character: DataModel) {
        val updatedCharacter = character.copy(isFavorite = !character.isFavorite)
        dao.insertFavorite(updatedCharacter)
    }
}