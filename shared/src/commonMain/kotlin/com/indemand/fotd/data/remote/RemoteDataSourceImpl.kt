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

class RemoteDataSourceImpl(private val httpClient: HttpClient) : RemoteDataSource {
    override suspend fun fetchConfiguration(): HttpResponse {
        return httpClient.get("https://raw.githubusercontent.com/kansalmohit19/configs/refs/heads/master/fotd/app-config.json") /*{
            parameter("app_version", request.app_version)
            parameter("device_type", request.device_type)
        }*/
    }

    override suspend fun dailyFact(accessToken: String): HttpResponse {
        return httpClient.get("http://152.67.10.2:8080/fact/today") {
            parameter("access_token", accessToken)
        }
    }

    override suspend fun loginUser(request: LoginUserRequest): HttpResponse {
        return httpClient.post("http://152.67.10.2:8080/user/login") {
            //contentType(ContentType.Application.FormUrlEncoded)
            setBody(FormDataContent(request.toParameters()))
        }
    }

    override suspend fun factsList(accessToken: String): HttpResponse {
        return httpClient.get("http://152.67.10.2:8080/fact/featured") {
            parameter("access_token", accessToken)
        }
    }
}