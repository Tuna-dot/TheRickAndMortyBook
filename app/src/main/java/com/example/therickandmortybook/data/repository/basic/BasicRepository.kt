package com.example.therickandmortybook.data.repository.basic

import com.example.therickandmortybook.utils.UiState
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BasicRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): UiState<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful && response.body() != null) {
                UiState.Success(response.body()!!)
            } else {
                UiState.Error(Exception("Ошибка сети"))
                when (response.code()) {
                    401 -> {
                        UiState.Error(Exception("Ошибка Авторизации ${response.code()}"))
                    }

                    404 -> {
                        UiState.Error(Exception("Ошибка 404 ${response.code()}"))
                    }

                    500 -> {
                        UiState.Error(Exception("Ошибка 500 ${response.code()}"))
                    }

                    else -> {
                        val message = response.errorBody()?.string() ?: "Неизвестная Ошибка"
                        UiState.Error(Exception("$message ${response.code()}"))
                    }
                }
            }
        } catch (e: HttpException) {
            UiState.Error(Exception("HTTP ошибка: ${e.code()} - ${e.message()}"))
        } catch (e: SocketTimeoutException) {
            UiState.Error(Exception(e.message ?: "Тайм-аут подключения"))
        } catch (e: UnknownHostException) {
            UiState.Error(Exception(e.message ?: "Нет подключения к сети"))
        } catch (e: IOException) {
            UiState.Error(Exception(e.message ?: "Ошибка ввода-вывода"))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}