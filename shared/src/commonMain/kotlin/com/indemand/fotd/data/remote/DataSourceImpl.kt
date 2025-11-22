package com.indemand.fotd.data.remote

import com.indemand.fotd.data.model.AppVersionRequest
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.model.toParameters
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class DataSourceImpl(private val httpClient: HttpClient) {
    suspend fun checkForAppVersion(request: AppVersionRequest): HttpResponse {
        return httpClient.get("http://152.67.10.2:8080/app/version") {
            parameter("app_version", request.app_version)
            parameter("device_type", request.device_type)
        }
    }

    suspend fun dailyFact(): HttpResponse {
        return httpClient.get("http://152.67.10.2:8080/fact/today") {
            parameter("access_token", "1dca5d0f526cbc9e28e5ddddf0aa8931")
        }
    }

    suspend fun loginUser(request: LoginUserRequest): HttpResponse {
        return httpClient.post("http://152.67.10.2:8080/user/login") {
            //contentType(ContentType.Application.FormUrlEncoded)
            setBody(FormDataContent(request.toParameters()))
        }
    }
}