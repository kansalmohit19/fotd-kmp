package com.indemand.fotd.core

import com.indemand.fotd.data.model.ConfigurationDTO

interface CommonResponse {
    val status: Int?
    val message: String?
}

val test = ConfigurationDTO
