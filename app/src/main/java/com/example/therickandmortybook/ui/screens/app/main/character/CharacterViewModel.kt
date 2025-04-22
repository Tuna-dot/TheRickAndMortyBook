package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.repository.pagerRepository.PagerRepository

class CharacterViewModel(private val pagerRepository: PagerRepository) : ViewModel() {
    val charactersPaging = pagerRepository.getCharacters()
        .cachedIn(viewModelScope)
}