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

class UserApiImpl(private val httpClient: HttpClient) : UserApi {
    override suspend fun dailyFact(url: String, accessToken: String): HttpResponse {
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

    override suspend fun validateToken(request: LoginUserRequest): HttpResponse {
        return httpClient.post("http://152.67.10.2:8080/user/loginViaAccessToken") {
            setBody(FormDataContent(request.toParameters()))
        }
    }

    override suspend fun factsList(url: String, accessToken: String): HttpResponse {
        return httpClient.get(url) {
            parameter("access_token", accessToken)
        }
    }
}