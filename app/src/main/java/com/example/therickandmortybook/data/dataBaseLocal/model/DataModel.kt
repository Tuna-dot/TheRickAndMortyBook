package com.example.therickandmortybook.data.dataBaseLocal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.therickandmortybook.data.dataBaseLocal.convertor.Converters
import com.example.therickandmortybook.data.model.charcter.OriginDto
import com.example.therickandmortybook.data.model.charcter.LocationDto

@Entity(tableName = "characters")
data class DataModel(
    @PrimaryKey val id: Int,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    @TypeConverters(Converters::class)
    val origin: OriginDto?,
    @TypeConverters(Converters::class)
    val location: LocationDto?,
    val image: String? = null,
    val episode: List<String?>? = null,
    val url: String? = null,
    val created: String? = null,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
)
