package com.example.therickandmortybook.ui.screens.app.main.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CharacterViewModel(private val pagerRepository: PagerRepository) : ViewModel() {

    private val reloadTrigger = MutableStateFlow(Unit) // Триггер для перезапуска

    @OptIn(ExperimentalCoroutinesApi::class)
    val characters: Flow<PagingData<DataModel>> = reloadTrigger.flatMapLatest {
        pagerRepository.getCharacters()
            .cachedIn(viewModelScope)
    }

    // Метод для работы с избранным (добавление/удаление)
    fun onFavoriteClick(characterId: Int) {
        viewModelScope.launch {
            val character = pagerRepository.getCharacterById(characterId)
            if (character != null) {
                pagerRepository.toggleFavorite(character) // Переключаем статус избранного
                reload() // Перезапускаем загрузку персонажей
            }
        }
    }

    // Перезапуск загрузки
    private fun reload() {
        reloadTrigger.value = Unit // Просто "пинаем" триггер, чтобы обновить список
    }
}
