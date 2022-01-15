package com.gilar.awesomeapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gilar.awesomeapp.util.LIST_VIEW_TYPE_GRID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Preference Data Store
 * A data layer contains application data
 */

val LIST_VIEW_TYPE = stringPreferencesKey("list_view_type")

class AppPreferenceRepository(
    private val context: Context
) {

    suspend fun saveListViewType(listViewType: String) {
        context.appPreferencesDataStore.edit { preferences ->
            preferences[LIST_VIEW_TYPE] = listViewType
        }
    }

    fun getListViewType(): Flow<String> = context.appPreferencesDataStore.data
        .map { preferences ->
            preferences[LIST_VIEW_TYPE] ?: LIST_VIEW_TYPE_GRID
        }
}
