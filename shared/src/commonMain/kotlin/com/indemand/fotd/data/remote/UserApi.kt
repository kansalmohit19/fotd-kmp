package com.indemand.fotd.data.remote

import com.indemand.fotd.data.model.LoginUserRequest
import io.ktor.client.statement.HttpResponse

interface UserApi {
    suspend fun loginUser(request: LoginUserRequest): HttpResponse

    suspend fun validateToken(request: LoginUserRequest): HttpResponse

    suspend fun dailyFact(
        url: String,
        accessToken: String,
    ): HttpResponse

    suspend fun factsList(
        url: String,
        accessToken: String,
    ): HttpResponse
}
