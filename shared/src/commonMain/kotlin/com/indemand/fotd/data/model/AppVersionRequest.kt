package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppVersionRequest(
    val device_type: Int = 2, val app_version: Int = 167
)
