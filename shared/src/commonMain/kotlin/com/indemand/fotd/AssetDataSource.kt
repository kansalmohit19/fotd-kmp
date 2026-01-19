package com.indemand.fotd

interface AssetDataSource {
    fun loadAssetFile(path: String): String
}