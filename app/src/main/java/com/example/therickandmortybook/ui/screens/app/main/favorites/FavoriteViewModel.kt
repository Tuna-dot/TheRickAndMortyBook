package com.example.therickandmortybook.ui.screens.app.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel.Companion.toResultDto
import com.example.therickandmortybook.data.repository.character.PagerRepository
import kotlinx.coroutines.flow.Flow
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
