package com.indemand.fotd

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

actual class KeyValueStorage(private val context: Context) {

    /*private val dataStore = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("settings")
    }*/

    actual fun putString(key: String, value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //dataStore.edit { it[stringPreferencesKey(key)] = value }
        }
    }

    actual fun getString(key: String): String? = runBlocking {
        //dataStore.data.first()[stringPreferencesKey(key)]
        ""
    }

    actual fun remove(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //dataStore.edit { it.remove(stringPreferencesKey(key)) }
        }
    }
}