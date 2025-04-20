import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.model.ResultDto

class CharactersPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, ResultDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page)

            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? {
        return state.anchorPosition
    }
}
