package com.example.therickandmortybook.ui.screens.app.main.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.therickandmortybook.data.repository.location.LocationRepository

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {
    val locationFlow = locationRepository.getLocation()
        .cachedIn(viewModelScope)
}