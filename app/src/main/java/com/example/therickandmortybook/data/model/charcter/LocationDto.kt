package com.example.therickandmortybook.data.model.charcter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)