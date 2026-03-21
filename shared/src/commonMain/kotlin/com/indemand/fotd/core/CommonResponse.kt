package com.indemand.fotd.core

import com.indemand.fotd.data.model.ConfigurationDTO
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

interface CommonResponse {
    val status: Int?
    val message: String?
}

val test = ConfigurationDTO