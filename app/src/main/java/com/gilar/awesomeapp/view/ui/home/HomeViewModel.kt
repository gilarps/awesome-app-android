package com.gilar.awesomeapp.view.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gilar.awesomeapp.data.local.AppPreferenceUseCase
import com.gilar.awesomeapp.data.model.Photo
import com.gilar.awesomeapp.data.remote.PexelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PexelsRepository,
    private val appPreferenceUseCase: AppPreferenceUseCase,
) : ViewModel() {


    val listViewType = MutableLiveData<String>()

    /**
     * Get photos from server using pagination
     * */
    fun getPhotos(): Flow<PagingData<Photo>> {
        return repository.getPhotos().cachedIn(viewModelScope)
    }

    /**
     * Get list view type from data store
     * */
    fun getListViewType() {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferenceUseCase.getListViewType().collect {
                listViewType.postValue(it)
            }
        }
    }

    /**
     * Set list view type in data store
     * */
    fun setListViewType(value: String){
        viewModelScope.launch(Dispatchers.IO) {
            appPreferenceUseCase.saveListViewType(value)
            listViewType.postValue(value)
        }
    }

}