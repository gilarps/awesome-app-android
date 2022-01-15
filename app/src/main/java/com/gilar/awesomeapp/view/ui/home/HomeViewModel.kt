package com.gilar.awesomeapp.view.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gilar.awesomeapp.data.Photo
import com.gilar.awesomeapp.network.AwesomeRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val repository: AwesomeRepository
) : ViewModel() {

    /**
     * Getting photos from server using pagination
     * */
    fun getPhotos(): Flow<PagingData<Photo>> {
        return repository.getPhotos().cachedIn(viewModelScope)
    }

}