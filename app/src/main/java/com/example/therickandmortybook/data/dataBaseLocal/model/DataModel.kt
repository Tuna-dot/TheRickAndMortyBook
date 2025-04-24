package com.example.therickandmortybook.data.dataBaseLocal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.therickandmortybook.data.model.charcter.LocationDto
import com.example.therickandmortybook.data.model.charcter.OriginDto

@Entity(tableName = "characters")
data class DataModel(
    @PrimaryKey val id: Int,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: OriginDto? = null,
    val location: LocationDto? = null,
    val image: String? = null,
    val episode: List<String?>? = null,
    val url: String? = null,
    val created: String? = null,
    val isFavorite: Boolean = false
)
