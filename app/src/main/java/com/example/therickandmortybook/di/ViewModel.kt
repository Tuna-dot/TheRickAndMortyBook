package com.example.therickandmortybook.di

import com.example.therickandmortybook.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        MainViewModel(
            apiService = get()
        )
    }
}