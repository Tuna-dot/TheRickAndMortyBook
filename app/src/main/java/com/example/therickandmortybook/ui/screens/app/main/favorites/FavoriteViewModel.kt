package com.example.therickandmortybook.ui.screens.app.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.map.toDataModel
import com.example.therickandmortybook.data.map.toResultDto
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val pagerRepository: PagerRepository
) : ViewModel() {

    val favoritesFlow: Flow<PagingData<DataModel>> =
        pagerRepository.getFavorites()
            .cachedIn(viewModelScope)

    fun removeFromFavorites(id: DataModel) {
        viewModelScope.launch {
            pagerRepository.deleteFavorites(id.toResultDto())
        }
    }
}
