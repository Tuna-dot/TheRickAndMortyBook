package com.example.therickandmortybook.data.repository.character.characterById

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.repository.basic.BasicRepository
import com.example.therickandmortybook.utils.UiState

class CharacterRepository(
    private val apiService: ApiService
) : BasicRepository() {
    suspend fun getCharacterById(characterId: Int): UiState<ResultDto> {
        return safeApiCall {
            apiService.getCharacterById(characterId)
        }
    }

}