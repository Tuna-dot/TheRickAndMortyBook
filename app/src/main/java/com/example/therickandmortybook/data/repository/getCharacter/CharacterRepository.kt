package com.example.therickandmortybook.data.repository.getCharacter

import coil.network.HttpException
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.ResultDto
import com.example.therickandmortybook.utils.UiState
import java.net.SocketTimeoutException

class CharacterRepository(
    private val apiService: ApiService
) {
    suspend fun getCharacterById(characterId: Int): UiState<ResultDto> {
        return try {
            val response = apiService.getCharacterById(characterId)
            if (response.isSuccessful && response.body() != null) {
                UiState.Success(response.body()!!)
            }else{
                UiState.Error(Exception("Ошибка сети"))
            }
        }catch (e: HttpException){
            UiState.Error(e)
        }catch (e: SocketTimeoutException){
            UiState.Error(e)
        }catch (e : Exception){
            UiState.Error(e)
        }
    }
}