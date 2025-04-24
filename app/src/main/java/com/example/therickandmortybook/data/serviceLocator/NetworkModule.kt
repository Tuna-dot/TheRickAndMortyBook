package com.example.therickandmortybook.data.serviceLocator

import androidx.room.Room
import com.example.therickandmortybook.data.datasource.characterPagingSource.CharactersPagingSource
import com.example.therickandmortybook.BuildConfig
import com.example.therickandmortybook.data.dataBaseLocal.AppDataBase
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.episodesPagingSource.EpisodesPagingSource
import com.example.therickandmortybook.data.datasource.locationPagingSource.LocationPagingSource
import com.example.therickandmortybook.data.repository.character.characterById.CharacterRepository
import com.example.therickandmortybook.data.repository.character.PagerRepository
import com.example.therickandmortybook.data.repository.episode.EpisodesRepository
import com.example.therickandmortybook.data.repository.episode.episodeById.EpisodeByIdRepository
import com.example.therickandmortybook.data.repository.location.LocationRepository
import com.example.therickandmortybook.data.repository.location.locationById.LocationByIdRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {
    //Retrofit
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(loggingInterceptor = get()) }
    single { provideJson() }
    single { provideRetrofit(okHttpClient = get(), jsonConverter = get()) }
    single { provideApiService(retrofit = get()) }
    //Characters
    single { CharactersPagingSource(apiService = get(),
        dao = get()) }
    single { PagerRepository(apiService = get(),
        dao = get(),
        io = get(named("IO"))) }
    //CharacterById
    single { CharacterRepository(apiService = get()) }
    //Locations
    single { LocationPagingSource(apiService = get()) }
    single { LocationRepository(apiService = get()) }
    //LocationById
    single { LocationByIdRepository(apiService = get()) }
    //Episodes
    single { EpisodesPagingSource(apiService = get()) }
    single { EpisodesRepository(apiService = get()) }
    //EpisodeById
    single { EpisodeByIdRepository(apiService = get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient, jsonConverter: Converter.Factory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.THE_RICK_AND_MORTY_API)
        .client(okHttpClient)
        .addConverterFactory(jsonConverter)
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideJson(): Converter.Factory {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    return json.asConverterFactory("application/json".toMediaType())
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
