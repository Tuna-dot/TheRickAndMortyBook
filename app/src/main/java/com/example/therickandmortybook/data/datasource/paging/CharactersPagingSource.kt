package com.example.therickandmortybook.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.ResultDto
import retrofit2.HttpException
import java.net.SocketTimeoutException

class CharactersPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, ResultDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page)
            val result = response.results.orEmpty()
            val safePage = page.coerceAtLeast(1)

            val prevKey = if (safePage > 1) safePage.minus(1) else null
            val nextKey = if (result.isNotEmpty()) safePage.plus(1) else null

            LoadResult.Page(
                data = result,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}