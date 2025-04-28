package com.example.therickandmortybook.data.repository.character

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.characterPagingSource.CharactersPagingSource
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

    suspend fun getCharacterById(id: Int): DataModel? {
        return withContext(io) {
            dao.getCharacterById(id)
        }
    }

    suspend fun getFavorites(): Flow<List<DataModel>> {
        return dao.getFavorites()
        dao.getFavorites().collect { favorites ->
            Log.d("ololo", "$favorites.toString()")
        }
    }

    // Убираем из избранного
    suspend fun removeFromFavorites(id: Int) {
        withContext(io) {
            val character = dao.getCharacterById(id)
            if (character != null) {
                val updatedCharacter = character.copy(isFavorite = false) // Убираем из избранного
                dao.updateFavorite(updatedCharacter) // Обновляем запись в базе данных
            }
        }
    }

    // Переключаем избранное
    suspend fun toggleFavorite(character: DataModel) {
        withContext(io) {
            val existingCharacter = dao.getCharacterById(character.id)
            val updatedCharacter =
                character.copy(isFavorite = !(existingCharacter?.isFavorite ?: false))

            // Вставляем или обновляем в базе данных
            if (existingCharacter != null) {
                dao.updateFavorite(updatedCharacter) // Обновляем запись в базе данных
            } else {

                dao.insertFavorite(updatedCharacter) // Вставляем новый элемент
            }
        }
    }
}