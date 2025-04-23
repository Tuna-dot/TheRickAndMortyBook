package com.example.therickandmortybook.data.model.episodes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDte(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("air_date")
    val airDate: String? = null,
    @SerialName("episode")
    val episode: String? = null,
    @SerialName("characters")
    val characters: List<String?>? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("created")
    val created: String? = null
)