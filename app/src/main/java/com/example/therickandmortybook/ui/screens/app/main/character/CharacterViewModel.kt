package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.map.toDataModel
import com.example.therickandmortybook.data.map.toResultDto
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val pagerRepository: PagerRepository
) : ViewModel() {

    private val _showFavorites = MutableStateFlow(false)
    val showFavorites: StateFlow<Boolean> get() = _showFavorites

    private val _characterListFlow = MutableStateFlow<PagingData<DataModel>>(PagingData.empty())
    val characterListFlow: StateFlow<PagingData<DataModel>> get() = _characterListFlow

    private fun getCharacterFlow(): Flow<PagingData<DataModel>> {
        return pagerRepository.getCharacters()
            .map { pagingData ->
                pagingData.map { dto: ResultDto ->
                    dto.toDataModel(isFavorite = dto.isFavorite)
                }
            }
            .cachedIn(viewModelScope)
    }

    fun toggleFavorites() {
        _showFavorites.value = !_showFavorites.value
    }

    fun onFavoriteClick(character: DataModel) {
        viewModelScope.launch {
            if (character.isFavorite) {
                pagerRepository.deleteFavorites(character.toResultDto())
            } else {
                pagerRepository.addFavorites(character.toResultDto().copy(isFavorite = true))
            }
            _characterListFlow.value = _characterListFlow.value.map { dataModel ->

                if (dataModel.id == character.id) {
                    dataModel.copy(isFavorite = !dataModel.isFavorite)
                } else {
                    dataModel
                }
            }

        }
    }

    init {
        viewModelScope.launch {
            _characterListFlow.value = getCharacterFlow().first()
        }
    }
}
