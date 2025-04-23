package com.example.therickandmortybook.ui.screens.app.main.location.locationDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.locatiion.ResultDta
import com.example.therickandmortybook.data.repository.character.characterById.CharacterRepository
import com.example.therickandmortybook.data.repository.location.locationById.LocationByIdRepository
import com.example.therickandmortybook.ui.screens.app.BaseViewModel
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val locationByIdRepository: LocationByIdRepository,
    private val charactersRepository: CharacterRepository
): BaseViewModel<ResultDta>() {
    fun getLocationById(locationId: Int){
        loadData {
            locationByIdRepository.getLocationById(locationId)
        }
    }
    private val _characters = MutableStateFlow<List<ResultDto>>(emptyList())
    val characters: StateFlow<List<ResultDto>> = _characters

    fun getResidents(residents: List<String?>?) {
        viewModelScope.launch {
            val result = residents
                ?.mapNotNull { it?.substringAfterLast("/")?.toIntOrNull() }
                ?.mapNotNull { id ->
                    val response = charactersRepository.getCharacterById(id)
                    if (response is UiState.Success) response.data else null
                } ?: emptyList()
            _characters.value = result
        }
    }

}