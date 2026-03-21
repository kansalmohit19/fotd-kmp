package com.indemand.fotd.data.remote

import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.model.toParameters
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class ConfigApiImpl(private val httpClient: HttpClient) : ConfigApi {
    override suspend fun fetchConfiguration(): HttpResponse {
        return httpClient.get("https://raw.githubusercontent.com/kansalmohit19/configs/refs/heads/master/releases/config.json") /*{
            parameter("app_version", request.app_version)
            parameter("device_type", request.device_type)
        }*/
    }
}