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

    @Update
    suspend fun updateFavorite(character: DataModel)

    @Insert
    suspend fun insertFavorite(character: DataModel)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): DataModel?

    @Query("SELECT * FROM characters")
    fun getFavorites(): Flow<List<DataModel>>

    @Query("SELECT id FROM characters")
    suspend fun getFavoritesOnce(): List<Int>
}
