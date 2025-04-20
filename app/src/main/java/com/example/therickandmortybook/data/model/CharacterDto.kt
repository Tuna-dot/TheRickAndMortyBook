package com.example.therickandmortybook.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("info")
    val info: InfoDto? = null,
    @SerialName("results")
    val results: List<ResultDto>? = null
)