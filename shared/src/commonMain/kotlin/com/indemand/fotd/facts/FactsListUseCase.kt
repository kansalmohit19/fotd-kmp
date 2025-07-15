package com.indemand.fotd.facts

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class FactsListUseCase(private val factsListService: FactsListService) {

    suspend fun getListOfFacts(): List<FactDetails> {
        val listOfFacts = factsListService.getListOfFacts()
        return listOfFacts?.map { fact ->
            FactDetails(
                imageUrl = fact.imageUrl,
                titleText = fact.title,
                descriptionText = fact.description ?: "No description available",
                postedOnDate = fact.postedOn?.let { getFormattedDate(it) } ?: "Unknown date",
                postedBy = fact.postedBy ?: "Anonymous"
            )
        } ?: emptyList()
    }


    private fun getFormattedDate(inputDate: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(inputDate).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        return when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }
    }
}