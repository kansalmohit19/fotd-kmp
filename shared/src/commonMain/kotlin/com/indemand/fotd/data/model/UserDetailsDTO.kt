package com.indemand.fotd.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
data class UserDetailsDTO(
    var user_id: Int?,
    val access_token: String?,
    val name: String?,
    val email: String?,
    val is_guest: Int?,
    val notification_enabled: Int?,
    val reward_points: Int?,
    val profile_image: String?
)