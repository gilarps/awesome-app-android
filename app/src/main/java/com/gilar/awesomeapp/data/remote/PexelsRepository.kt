package com.gilar.awesomeapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gilar.awesomeapp.data.model.Photo
import com.gilar.awesomeapp.data.model.PhotoPagingSource
import kotlinx.coroutines.flow.Flow

class PexelsRepository(private val service: PexelsService) {

    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PhotoPagingSource(service) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 8
    }
}
