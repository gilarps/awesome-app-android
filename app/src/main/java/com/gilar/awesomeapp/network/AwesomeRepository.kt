package com.gilar.awesomeapp.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gilar.awesomeapp.data.Photo
import com.gilar.awesomeapp.data.PhotoPagingSource
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class AwesomeRepository(private val service: AwesomeService) {

    fun getPhotos(): Flow<PagingData<Photo>> {
        Timber.e("getPhotos() in repo")
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PhotoPagingSource(service) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 8
    }
}
