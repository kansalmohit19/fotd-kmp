package com.indemand.fotd

import android.content.Context

class AndroidAssetDataSource(
    private val applicationContext: Context,
) : AssetDataSource {
    override fun loadAssetFile(path: String): String {
        println("HERE: $path")
        return applicationContext.assets
            .open(path)
            .bufferedReader()
            .use { it.readText() }
    }
}
