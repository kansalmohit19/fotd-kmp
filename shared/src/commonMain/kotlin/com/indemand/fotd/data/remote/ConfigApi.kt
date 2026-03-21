package com.indemand.fotd.data.remote

import com.indemand.fotd.data.model.LoginUserRequest
import io.ktor.client.statement.HttpResponse

interface ConfigApi {

    suspend fun fetchConfiguration(): HttpResponse
}