package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.room.util.copy
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel.Companion.toResultDto
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.charcter.ResultDto.Companion.toDataModel
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModel(
    private val pagerRepository: PagerRepository
) : ViewModel() {

    private val _showFavorites = MutableStateFlow(false)
    val showFavorites: StateFlow<Boolean> get() = _showFavorites

    private val _characterListFlow = MutableStateFlow<PagingData<DataModel>>(PagingData.empty())
    val characterListFlow: StateFlow<PagingData<DataModel>> get() = _characterListFlow

    private val _characterFilterStateFlow = MutableStateFlow(FilterState())
    val characterFilterStateFlow: StateFlow<FilterState> get() = _characterFilterStateFlow


    init {
        viewModelScope.launch {
            _characterListFlow.value = getCharacterFlow().first()
        }
    }

    fun updateFilter(
        name: String = "",
        status: String = "",
        species: String = "",
        type: String = "",
        gender: String = ""
    ) {
        _characterFilterStateFlow.value = FilterState(
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender)
        viewModelScope.launch {
            _characterListFlow.value = getCharactersFilter(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender
            ).map {
                it.map { dto: ResultDto ->
                    dto.toDataModel(isFavorite = dto.isFavorite)
                }
            }.first()
        }
    }


    private fun getCharacterFlow(): Flow<PagingData<DataModel>> {
        return pagerRepository.getCharacters()
            .map { pagingData ->
                pagingData.map { dto: ResultDto ->
                    dto.toDataModel(isFavorite = dto.isFavorite)

                }
            }
            .cachedIn(viewModelScope)
    }

    fun getCharactersFilter(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): Flow<PagingData<ResultDto>> {
        return pagerRepository.getCharacters(
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender
        ).map { pagingData ->
            pagingData.map { dto: ResultDto ->
                dto.copy(isFavorite = dto.isFavorite)
            }
        }
    }

    fun resestFilter(){
        _characterFilterStateFlow.value = FilterState()

        viewModelScope.launch {
            _characterListFlow.value = getCharacterFlow().first()
        }
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
    data class FilterState(
        val name: String = "",
        val status: String = "",
        val species: String = "",
        val type: String = "",
        val gender: String = ""
    )
}