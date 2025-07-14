package com.indemand.fotd.facts

import kotlinx.serialization.Serializable

@Serializable
data class FactDetailsDTO(
    val imageUrl: String,
    val title: String,
    val description: String?,
    val postedBy: String?,
    val postedOn: String?
)