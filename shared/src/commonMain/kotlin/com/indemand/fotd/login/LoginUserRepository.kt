package com.indemand.fotd.login

import com.indemand.fotd.CommonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.http.formUrlEncode

class LoginUserRepository(private val httpClient: HttpClient) {
    suspend fun loginUser(username: String, password: String): UserAPIResponse? {
        val response: CommonResponse<UserAPIResponse> =
            httpClient.post("http://152.67.10.2:8080/user/login") {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    Parameters.build {
                        append("email", username)
                        append("password", password)
                        append("device_token", "abc")
                        append("device_type", "2")
                        append("device_name", "ANDROID")
                    }.formUrlEncode()
                )
            }.body()
        println("Response status: ${response.status}")
        //println("Response body: ${response.bodyAsText()}")
        return response.data
    }
}