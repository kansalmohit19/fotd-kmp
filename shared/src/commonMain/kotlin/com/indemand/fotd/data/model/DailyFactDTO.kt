package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class DailyFactDTO(
    override val status: Int? = null,
    override val message: String? = null,
    val data: DailyFactDataDTO? = null,
) : CommonResponse

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class DailyFactDataDTO(
    val fact: FactDetailsDTO? = null,
    val like_count: Int? = null,
    val dislike_count: Int? = null,
)
