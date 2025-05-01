package com.example.therickandmortybook.data.map

import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.model.charcter.ResultDto

fun ResultDto.toDataModel(
    isFavorite: Boolean = false
): DataModel {
    return DataModel(
        id = this.id ?: 0,
        name = this.name,
        status = this.status,
        image = this.image,
        isFavorite = isFavorite
    )
}
fun DataModel.toResultDto(): ResultDto {
    return ResultDto(
        id = this.id,
        name = this.name,
        status = this.status,
        image = this.image,
        isFavorite = this.isFavorite
    )
}