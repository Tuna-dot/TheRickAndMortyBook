package com.example.therickandmortybook.data.serviceLocator

import android.app.Application
import androidx.room.Room
import com.example.therickandmortybook.data.dataBaseLocal.AppDataBase
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import org.koin.dsl.module

val localeModule = module {
    //DataBase
    single { provideDataBase(context = get()) }
    single { provideDao(dataBase = get()) }
}

fun provideDataBase(context: Application): AppDataBase {
    return Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "The_Rick_And_Morty_database"
    ).fallbackToDestructiveMigration()
        .build()
}

fun provideDao(dataBase: AppDataBase): NoteDao {
    return dataBase.noteDao()
}