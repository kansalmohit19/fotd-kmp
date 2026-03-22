package com.indemand.fotd.data.local

interface LocalDataSource {
    suspend fun saveString(key: String, value: String?)
    suspend fun getString(key: String): String?
    suspend fun saveJsonString(key: String, value: String?)
    suspend fun getJsonString(key: String): String?

    suspend fun clear()
}