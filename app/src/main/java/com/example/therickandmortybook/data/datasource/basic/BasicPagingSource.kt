package com.example.therickandmortybook.data.datasource.basic

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.collections.isNotEmpty

abstract class BasicPagingSource<T : Any>(
    private val loadData: suspend (pageIndex: Int) -> Response<List<T>>
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        return try {
            val response = loadData.invoke(page)
            if (
                response.isSuccessful &&
                response.body() != null
            ) {
                val result = response.body()!!

                val safePage = page.coerceAtLeast(1)

                val prevKey = if (safePage > 1) safePage.minus(1) else null
                val nextKey = if (response.body()!!.isNotEmpty()) safePage.plus(1) else null

                LoadResult.Page(
                    data = result,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                when (response.code()) {
                    401 -> {
                        LoadResult.Error(Exception("Ошибка авторизации"))
                    }

                    404 -> {
                        LoadResult.Error(Exception("Ошибка 404"))
                    }

                    500 -> {
                        LoadResult.Error(Exception("Ошибка 500"))
                    }

                    else -> {
                        val message = response.errorBody()?.string() ?: "Неизвестная Ошибка"
                        LoadResult.Error(Exception(message))
                    }
                }
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}