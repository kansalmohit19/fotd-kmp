package com.indemand.fotd.data.model

import io.ktor.http.Parameters
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    val email: String,
    val password: String,
    val device_token: String = "abc",
    val device_type: String = "2",
    val device_name: String = "ANDROID"
)

fun LoginUserRequest.toParameters(): Parameters = Parameters.build {
    append("email", email)
    append("password", password)
    append("device_token", device_token)
    append("device_type", device_type)
    append("device_name", device_name)
}
