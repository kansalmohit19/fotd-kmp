package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfoDTO(
    override val status: Int? = null,
    override val message: String? = null,
    val data: LoginInfoDataDTO? = null,
) : CommonResponse

@Serializable
data class LoginInfoDataDTO(
    val userInfo: UserDetailsDTO? = null,
)
