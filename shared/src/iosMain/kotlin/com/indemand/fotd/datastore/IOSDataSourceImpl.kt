package com.indemand.fotd.datastore

import com.indemand.fotd.data.local.LocalDataSource

class IOSDataSourceImpl : LocalDataSource {
    override suspend fun saveString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(key: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}