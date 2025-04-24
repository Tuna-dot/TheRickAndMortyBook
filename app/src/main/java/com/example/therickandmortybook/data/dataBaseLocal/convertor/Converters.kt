package com.example.therickandmortybook.data.dataBaseLocal.convertor

import androidx.room.TypeConverter
import com.example.therickandmortybook.data.model.charcter.LocationDto
import com.example.therickandmortybook.data.model.charcter.OriginDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromEpisodeList(value: List<String?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toEpisodeList(value: String): List<String?>? {
        val type = object : TypeToken<List<String?>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromOrigin(origin: OriginDto?): String = Gson().toJson(origin)

    @TypeConverter
    fun toOrigin(value: String): OriginDto? = Gson().fromJson(value, OriginDto::class.java)

    @TypeConverter
    fun fromLocation(location: LocationDto?): String = Gson().toJson(location)

    @TypeConverter
    fun toLocation(value: String): LocationDto? = Gson().fromJson(value, LocationDto::class.java)
}
