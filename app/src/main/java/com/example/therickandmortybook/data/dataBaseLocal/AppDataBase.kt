package com.example.therickandmortybook.data.dataBaseLocal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.therickandmortybook.data.dataBaseLocal.convertor.Converters
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel

@Database(
    entities = [DataModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase:RoomDatabase() {
    abstract fun noteDao(): NoteDao
}