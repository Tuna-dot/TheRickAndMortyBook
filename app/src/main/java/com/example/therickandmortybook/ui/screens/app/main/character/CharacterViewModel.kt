package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel(private val pagerRepository: PagerRepository) : ViewModel() {

    val characters: Flow<PagingData<DataModel>> = pagerRepository.getCharacters()
        .cachedIn(viewModelScope)

    val favorites: Flow<List<DataModel>> = pagerRepository.getFavorites()

    fun onFavoriteClick(characterId: Int) {
        viewModelScope.launch {
            val character = pagerRepository.getCharacterById(characterId)
            if (character != null) {
                pagerRepository.toggleFavorite(character)
            }
        }
    }
}