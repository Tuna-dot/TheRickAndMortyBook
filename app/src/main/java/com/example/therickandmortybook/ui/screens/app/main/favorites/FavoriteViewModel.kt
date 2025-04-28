package com.example.therickandmortybook.ui.screens.app.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.repository.character.PagerRepository
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: PagerRepository
) : ViewModel() {

    // Просто предоставляем Flow с избранными персонажами
    val favorites: Flow<List<DataModel>> = repository.getFavorites()

    private val _removeStatus = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))
    val removeStatus: StateFlow<UiState<Unit>> = _removeStatus

    fun removeFromFavorites(id: Int) {
        viewModelScope.launch {
            try {
                repository.removeFromFavorites(id)
                _removeStatus.value = UiState.Success(Unit)  // Успешное удаление
            } catch (e: Exception) {
                _removeStatus.value = UiState.Error(e) // Ошибка при удалении
            }
        }
    }
}

