package com.example.therickandmortybook.data.dataBaseLocal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel

@Database(
    entities = [DataModel::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}