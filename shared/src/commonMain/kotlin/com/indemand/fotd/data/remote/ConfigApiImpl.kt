package com.indemand.fotd.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ConfigApiImpl(private val httpClient: HttpClient) : ConfigApi {
    override suspend fun fetchConfiguration(url: String): HttpResponse {
        return httpClient.get(url) /*{
            parameter("app_version", request.app_version)
            parameter("device_type", request.device_type)
        }*/
    }
}