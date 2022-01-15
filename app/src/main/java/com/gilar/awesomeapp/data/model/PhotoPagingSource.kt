package com.gilar.awesomeapp.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gilar.awesomeapp.data.remote.PexelsRepository.Companion.NETWORK_PAGE_SIZE
import com.gilar.awesomeapp.data.remote.PexelsService
import retrofit2.HttpException
import java.io.IOException

private const val PEXELS_STARTING_PAGE_INDEX = 1

class PhotoPagingSource(
    private val service: PexelsService
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: PEXELS_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchPhotos(page, params.loadSize)
            val photos = response.photos
            val nextKey =
                if (photos.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    page + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = photos,
                prevKey = if (page == PEXELS_STARTING_PAGE_INDEX) null else page,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
