package com.example.therickandmortybook.ui.screens.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _state = MutableStateFlow<UiState<T>>(UiState.Empty)
    val state: StateFlow<UiState<T>> = _state

    protected fun loadData(block: suspend () -> T) {
        viewModelScope.launch {
            _state.value = UiState.Loading // Ставим состояние загрузки
            try {
                val data = block() // Получаем данные
                _state.value = UiState.Success(data)
            } catch (e: Exception) {
                _state.value = UiState.Error(e) // В случае ошибки оборачиваем в Error
            }
        }
    }
}
