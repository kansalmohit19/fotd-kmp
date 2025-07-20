package com.indemand.fotd.splash

import com.indemand.fotd.CommonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AppVersionRepository(private val httpClient: HttpClient) {
    suspend fun checkForAppVersion(): AppVersionResponse? {
        val response: CommonResponse<AppVersionResponse> =
            httpClient.get("http://152.67.10.2:8080/app/version?app_version=167&device_type=2")
                .body()
        return response.data
    }
}