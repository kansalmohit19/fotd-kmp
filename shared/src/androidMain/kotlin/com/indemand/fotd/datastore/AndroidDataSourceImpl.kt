package com.indemand.fotd.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.indemand.fotd.data.local.LocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AndroidDataSourceImpl(private val dataStore: DataStore<Preferences>) : LocalDataSource {

    override suspend fun saveString(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return dataStore.data.map { it[stringPreferencesKey(key)] }.firstOrNull()
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}