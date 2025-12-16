package com.indemand.fotd.data.local

interface LocalDataSource {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String): String?
    suspend fun clear()
}