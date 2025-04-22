package com.example.therickandmortybook.data.datasource

import com.example.therickandmortybook.data.model.CharacterDto
import com.example.therickandmortybook.data.model.ResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterDto

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<ResultDto>

}