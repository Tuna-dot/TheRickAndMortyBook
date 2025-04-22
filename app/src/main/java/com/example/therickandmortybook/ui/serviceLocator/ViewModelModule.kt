package com.example.therickandmortybook.ui.serviceLocator

import com.example.therickandmortybook.ui.screens.app.main.character.CharacterViewModel
import com.example.therickandmortybook.ui.screens.app.main.character.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharacterViewModel(
            get()
        )
    }
    viewModel {
        DetailViewModel(
            get()
        )
    }
}