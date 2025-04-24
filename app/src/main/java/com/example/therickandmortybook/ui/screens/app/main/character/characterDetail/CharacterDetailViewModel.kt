package com.example.therickandmortybook.ui.screens.app.main.character.characterDetail

import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.repository.character.characterById.CharacterRepository
import com.example.therickandmortybook.ui.screens.app.BaseViewModel
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository
) : BaseViewModel<ResultDto>() {

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            loadData {
                val uiState = characterRepository.getCharacterById(characterId)
                if (uiState is UiState.Success) {
                    uiState.data // Результат будет доступен здесь
                } else {
                    throw Exception("Error fetching data")
                }
            }
        }
    }
}