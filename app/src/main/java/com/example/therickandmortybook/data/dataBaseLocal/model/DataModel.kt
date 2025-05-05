package com.example.therickandmortybook.data.dataBaseLocal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.therickandmortybook.data.model.charcter.ResultDto


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
) {
    companion object {

        fun DataModel.toResultDto(): ResultDto {
            return ResultDto(
                id = this.id,
                name = this.name,
                status = this.status,
                image = this.image,
                isFavorite = this.isFavorite
            )
        }
    }
}
