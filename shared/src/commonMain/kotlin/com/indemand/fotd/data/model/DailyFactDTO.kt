package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyFactDTO(
    val fact: FactDetailsDTO, val like_count: Int?, val dislike_count: Int?
)