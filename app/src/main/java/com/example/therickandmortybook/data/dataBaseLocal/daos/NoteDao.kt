package com.example.therickandmortybook.data.dataBaseLocal.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertFavorite(character: DataModel)

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<DataModel>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): DataModel?

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Delete
    suspend fun removeFavorite(character: DataModel)
}