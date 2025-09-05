package com.indemand.fotd

expect class KeyValueStorage {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
    fun remove(key: String)
}