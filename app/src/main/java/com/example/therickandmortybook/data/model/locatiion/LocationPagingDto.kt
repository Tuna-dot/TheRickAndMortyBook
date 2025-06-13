package com.example.therickandmortybook.data.model.locatiion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationPagingDto(
    @SerialName("info")
    val info: InfoDto? = null,
    @SerialName("results")
    val results: List<ResultDta>? = null
)