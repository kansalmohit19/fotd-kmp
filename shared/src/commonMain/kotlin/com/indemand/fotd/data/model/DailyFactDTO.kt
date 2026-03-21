package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.Serializable

@Serializable
data class DailyFactDTO(
    override val status: Int?, override val message: String?,
    val fact: FactDetailsDTO, val like_count: Int?, val dislike_count: Int?,
) : CommonResponse