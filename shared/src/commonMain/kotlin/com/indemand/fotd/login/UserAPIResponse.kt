package com.indemand.fotd.login

import kotlinx.serialization.Serializable

@Serializable
data class UserAPIResponse(val userInfo: UserDetailsDTO?)