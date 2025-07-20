package com.indemand.fotd

import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    val status: Int? = 0, val message: String? = null, val data: T? = null
) {/*companion object {
        fun <T> loading(): CommonResponse<T> = CommonResponse(isLoading = true)
        fun <T> error(message: String): CommonResponse<T> = CommonResponse(errorMessage = message)
        fun <T> success(data: T): CommonResponse<T> = CommonResponse(data = data)
    }*/
}