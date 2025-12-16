package com.indemand.fotd.data.remote

import com.indemand.fotd.data.model.LoginUserRequest
import io.ktor.client.statement.HttpResponse

interface RemoteDataSource {

    suspend fun fetchConfiguration(): HttpResponse

    suspend fun dailyFact(accessToken: String): HttpResponse

    suspend fun loginUser(request: LoginUserRequest): HttpResponse

    suspend fun factsList(accessToken: String): HttpResponse
}