package com.indemand.fotd

interface PreferencesDataSource {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String): String?
    suspend fun clear()
}