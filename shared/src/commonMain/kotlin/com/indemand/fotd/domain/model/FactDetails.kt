package com.indemand.fotd.domain.model

data class FactDetails(
    val imageUrl: String,
    val title: String,
    val description: String,
    val likeCount: Int,
    val dislikeCount: Int,
    val postedBy: String,
    val postedOn: String,
)
