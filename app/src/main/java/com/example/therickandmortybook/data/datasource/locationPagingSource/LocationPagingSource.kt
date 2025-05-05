package com.example.therickandmortybook.data.datasource.locationPagingSource

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.basic.BasicPagingSource
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import retrofit2.Response

class LocationPagingSource(
    private val apiService: ApiService
) : BasicPagingSource<ResultDta>(
    loadData = { page ->
        val response = apiService.getLocation(page)
        if (response.isSuccessful &&
            response.body() != null
        ) {
            val body = response.body()!!
            Response.success(body.results ?: emptyList())
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }
)