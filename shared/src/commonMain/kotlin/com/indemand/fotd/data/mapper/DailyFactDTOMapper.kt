package com.indemand.fotd.data.mapper

import com.indemand.fotd.data.model.DailyFactDTO
import com.indemand.fotd.data.model.DailyFactDataDTO
import com.indemand.fotd.domain.model.FactDetails

fun DailyFactDataDTO?.toDomain(): FactDetails = FactDetails(
    imageUrl = "",
    title = this?.fact?.fact.orEmpty(),
    description = "",
    likeCount = this?.like_count ?: 0,
    dislikeCount = this?.dislike_count ?: 0,
    postedBy = "",
    postedOn = "",
)
