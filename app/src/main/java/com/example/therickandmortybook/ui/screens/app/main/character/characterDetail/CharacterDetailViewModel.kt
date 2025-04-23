package com.example.therickandmortybook.ui.screens.app.main.character.characterDetail

import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.repository.character.characterById.CharacterRepository
import com.example.therickandmortybook.ui.screens.app.BaseViewModel

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository
) : BaseViewModel<ResultDto>() {

    fun getCharacterById(characterId: Int) {
        loadData {
            characterRepository.getCharacterById(characterId)
        }
    }
}