package com.example.therickandmortybook.data.repository.basic

import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.utils.UiState
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BasicRepository{
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): UiState<T>{
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful && response.body() != null){
                UiState.Success(response.body()!!)
            }else{
                UiState.Error(Exception("Ошибка сети"))
            }
        }catch (e: HttpException) {
            UiState.Error(Exception("HTTP ошибка: ${e.code()} - ${e.message()}"))
        } catch (e: SocketTimeoutException) {
            UiState.Error(Exception("Тайм-аут подключения"))
        } catch (e: UnknownHostException) {
            UiState.Error(Exception("Нет подключения к сети"))
        } catch (e: IOException) {
            UiState.Error(Exception("Ошибка ввода-вывода"))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}