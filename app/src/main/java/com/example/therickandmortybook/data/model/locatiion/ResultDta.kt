package com.example.therickandmortybook.data.model.locatiion


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDta(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("dimension")
    val dimension: String? = null,
    @SerialName("residents")
    val residents: List<String?>? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("created")
    val created: String? = null
)