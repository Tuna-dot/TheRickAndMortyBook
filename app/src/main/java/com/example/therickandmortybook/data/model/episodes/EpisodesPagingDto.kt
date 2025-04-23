package com.example.therickandmortybook.data.model.episodes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesPagingDto(
    @SerialName("info")
    val info: InfoDto? = null,
    @SerialName("results")
    val results: List<ResultDte>? = null
)