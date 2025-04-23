package com.example.therickandmortybook.data.datasource.characterPagingSource

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.model.charcter.ResultDto
import retrofit2.Response

class CharactersPagingSource(
    private val apiService: ApiService
) : BasicPagingSource<ResultDto>(
    loadData = { page ->
        val response = apiService.getCharacters(page)
       if (response.isSuccessful &&
           response.body() != null){
           val body = response.body()!!
           Response.success(body.results ?: emptyList())
       }else{
           Response.error(response.code(), response.errorBody()!!)
       }
    }
)