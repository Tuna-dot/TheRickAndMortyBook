package com.example.therickandmortybook.data.dataBaseLocal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "characters")
data class DataModel(
    @PrimaryKey
    val id: Int,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val image: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = true
)
