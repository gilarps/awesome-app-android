package com.gilar.awesomeapp.data.local

import kotlinx.coroutines.flow.Flow

/**
 * Preference Data Store Domain Layer
 * Sits between the UI layer and data layer
 */
class AppPreferenceUseCase(
    private val repository: AppPreferenceRepository
) {

    /**
     * Get selected photos list view type
     * @return timestamp
     * */
    fun getListViewType(): Flow<String> {
        return repository.getListViewType()
    }

    /**
     * Save selected photos list view type
     * @param listViewType is list view type
     * */
    suspend fun saveListViewType(listViewType: String) {
        return repository.saveListViewType(listViewType)
    }
}
