package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class FactsListDTO(
    override val status: Int? = null,
    override val message: String? = null,
    val data: FactsListDataDTO? = null,
) : CommonResponse

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class FactsListDataDTO(
    val featured: List<FactDetailsDTO>? = null,
)
