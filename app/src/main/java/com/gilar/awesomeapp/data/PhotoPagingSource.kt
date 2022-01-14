package com.gilar.awesomeapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gilar.awesomeapp.network.AwesomeService

private const val PEXELS_STARTING_PAGE_INDEX = 1

class PhotoPagingSource(
    private val service: AwesomeService
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: PEXELS_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchPhotos(page, params.loadSize)
            val photos = response.photos
            LoadResult.Page(
                data = photos,
                prevKey = if (page == PEXELS_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (photos.size < params.loadSize) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
