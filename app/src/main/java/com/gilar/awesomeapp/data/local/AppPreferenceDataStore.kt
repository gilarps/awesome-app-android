package com.gilar.awesomeapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.gilar.awesomeapp.util.PREFERENCES_DATASTORE_NAME

/**
 * Application preference data store
 * Data storage solution that store key-value pairs
 * */
val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_DATASTORE_NAME
)