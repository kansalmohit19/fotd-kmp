package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FactsListDTO(
    val featured: List<FactDetailsDTO>?
)