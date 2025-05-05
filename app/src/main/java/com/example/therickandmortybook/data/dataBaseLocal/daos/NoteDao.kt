package com.example.therickandmortybook.data.dataBaseLocal.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM characters")
    fun fetchAllFavoriteCharacters(): Flow<List<DataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(character: DataModel)

    @Delete
    suspend fun deleteFavoriteCharacter(character: DataModel)
}
