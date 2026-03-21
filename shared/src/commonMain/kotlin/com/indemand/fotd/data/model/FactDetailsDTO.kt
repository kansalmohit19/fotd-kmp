package com.indemand.fotd.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
data class FactDetailsDTO(
    val imageUrl: String? = null,
    val fact: String? = null,
    val description: String? = null,
    val minimum_like_count: Int? = null,
    val minimum_dislike_count: Int? = null,
    val postedBy: String? = null,
    val postedOn: String? = null,
    val like_count: Int? = null,
    val dislike_count: Int? = null,
)