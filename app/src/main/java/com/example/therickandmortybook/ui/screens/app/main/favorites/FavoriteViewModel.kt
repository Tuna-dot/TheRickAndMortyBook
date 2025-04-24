package com.example.therickandmortybook.ui.screens.app.main.favorites

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    val favorites: Flow<List<DataModel>> = repository.getFavoriteCharacters()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun removeFromFavorites(character: DataModel) {
        viewModelScope.launch {
            repository.removeFavorite(character)
        }
    }
}