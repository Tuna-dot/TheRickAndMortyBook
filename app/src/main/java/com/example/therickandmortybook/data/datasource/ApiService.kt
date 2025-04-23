package com.example.therickandmortybook.data.datasource

import com.example.therickandmortybook.data.model.charcter.CharacterDto
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.episodes.EpisodesPagingDto
import com.example.therickandmortybook.data.model.episodes.ResultDte
import com.example.therickandmortybook.data.model.locatiion.LocationPagingDto
import com.example.therickandmortybook.data.model.locatiion.ResultDta

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<ResultDto>

    @GET("location")
    suspend fun getLocation(
        @Query("page") page: Int
    ): Response<LocationPagingDto>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") locationId: Int
    ): Response<ResultDta>

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ) : Response<EpisodesPagingDto>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") episodeId: Int
    ): Response<ResultDte>
}