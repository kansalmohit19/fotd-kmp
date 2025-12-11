package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FactDetailsDTO(
    val imageUrl: String? = null,
    val fact: String? = null,
    val description: String? = null,
    val minimum_like_count: String?,
    val minimum_dislike_count: String?,
    val postedBy: String? = null,
    val postedOn: String? = null,
    val like_count: String? = null,
    val dislike_count: String? = null,
)