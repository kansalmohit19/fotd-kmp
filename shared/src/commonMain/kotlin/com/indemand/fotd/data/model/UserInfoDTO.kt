package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(override val status: Int?, override val message: String?, val userInfo: UserDetailsDTO?) : CommonResponse