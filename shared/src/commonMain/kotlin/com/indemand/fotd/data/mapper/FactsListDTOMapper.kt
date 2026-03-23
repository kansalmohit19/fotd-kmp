package com.indemand.fotd.data.mapper

import com.indemand.fotd.data.model.FactsListDTO
import com.indemand.fotd.domain.model.FactDetails
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun FactsListDTO?.toDomain(): List<FactDetails> =
    this?.data?.featured?.map { item ->
        FactDetails(
            imageUrl = "",
            title = item.fact.orEmpty(),
            description = "",
            likeCount = item.like_count?.toInt() ?: 0,
            dislikeCount = item.dislike_count?.toInt() ?: 0,
            postedBy = "added by: " + (item.postedBy ?: "--"),
            postedOn = "added on: " + (item.postedOn?.let { getFormattedDate(it) } ?: "--"),
        )
    } ?: emptyList()

@OptIn(ExperimentalTime::class)
private fun getFormattedDate(inputDate: String): String {
    val today = Clock.System.todayIn(TimeZone.Companion.currentSystemDefault())
    val days =
        today.daysUntil(
            Instant.parse(inputDate).toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date,
        )

    return when {
        abs(days) > 1 -> "${abs(days)} days ago"
        abs(days) == 1 -> "yesterday"
        else -> "today"
    }
}
