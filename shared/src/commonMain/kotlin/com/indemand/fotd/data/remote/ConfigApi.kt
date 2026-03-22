package com.indemand.fotd.data.remote

import io.ktor.client.statement.HttpResponse

interface ConfigApi {

    suspend fun fetchConfiguration(url: String): HttpResponse
}