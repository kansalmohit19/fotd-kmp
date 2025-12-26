package com.indemand.fotd.data.model

import io.ktor.http.Parameters
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    val email: String? = null,
    val password: String? = null,
    val accessToken: String? = null,
    var deviceToken: String? = null,
    val deviceType: String = "2",
    val deviceName: String = "ANDROID"
)

fun LoginUserRequest.toParameters(): Parameters = Parameters.build {
    email?.let { append("email", it) }
    password?.let { append("password", it) }
    accessToken?.let { append("access_token", it) }
    deviceToken?.let { append("device_token", it) }
    append("device_type", deviceType)
    append("device_name", deviceName)
}
