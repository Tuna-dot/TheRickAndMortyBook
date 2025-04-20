package com.example.therickandmortybook.ui.viewmodel

import CharactersPagingSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService) : ViewModel() {
    private val _charactersPaging = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { CharactersPagingSource(apiService) }
    ).flow.cachedIn(viewModelScope)

    val charactersPaging: Flow<PagingData<ResultDto>> = _charactersPaging
    var searchQuery by mutableStateOf("")

    private val _characterState = MutableStateFlow<UiState<ResultDto>>(UiState.Empty)
    val characterState: StateFlow<UiState<ResultDto>> = _characterState

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            _characterState.value = UiState.Loading
             try {
                val response = apiService.getCharacterById(characterId)
                if(response != null){
                    _characterState.value = UiState.Success(response)
                } else {
                    _characterState.value = UiState.Error(error = Exception("Character not found"))
                }

            } catch (e: Exception) {
                _characterState.value = UiState.Error(error = e)
            }
        }
    }
}