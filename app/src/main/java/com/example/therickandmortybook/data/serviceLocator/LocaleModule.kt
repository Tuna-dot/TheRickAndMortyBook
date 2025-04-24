package com.example.therickandmortybook.data.serviceLocator

import android.app.Application
import androidx.room.Room
import com.example.therickandmortybook.data.dataBaseLocal.AppDataBase
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.repository.favorite.FavoriteRepository
import org.koin.dsl.module

val localeModule = module {
    //DataBase
    single { provideDataBase(context = get()) }
    single { provideDao(dataBase = get()) }
    single {FavoriteRepository(dao = get()) }
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