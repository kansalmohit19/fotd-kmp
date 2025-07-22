package com.indemand.fotd.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CommonResponse(
    val status: Int? = 0, val message: String? = null, val data: JsonElement? = null
) {/*companion object {
        fun <T> loading(): CommonResponse<T> = CommonResponse(isLoading = true)
        fun <T> error(message: String): CommonResponse<T> = CommonResponse(errorMessage = message)
        fun <T> success(data: T): CommonResponse<T> = CommonResponse(data = data)
    }*/
}