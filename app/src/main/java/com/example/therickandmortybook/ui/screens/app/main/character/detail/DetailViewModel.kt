package com.example.therickandmortybook.ui.screens.app.main.character.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.data.repository.getCharacter.CharacterRepository
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val characterRepository: CharacterRepository
): ViewModel() {
    private val _characterState = MutableStateFlow<UiState<ResultDto>>(UiState.Empty)
    val characterState: StateFlow<UiState<ResultDto>> = _characterState

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            _characterState.value = UiState.Loading
            val response = characterRepository.getCharacterById(characterId)
            _characterState.value = response
            Log.e("ololo", "getCharacterById: ", )
        }
    }
}