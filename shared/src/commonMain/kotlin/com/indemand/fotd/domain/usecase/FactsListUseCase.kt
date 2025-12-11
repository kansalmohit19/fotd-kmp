package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.FactsListRepository
import com.indemand.fotd.domain.model.FactDetails

class FactsListUseCase(private val factsListRepository: FactsListRepository) :
    UseCase<Unit, List<FactDetails>>() {

    override suspend fun run(params: Unit): Either<List<FactDetails>, IFailure> {
        return factsListRepository.getListOfFacts()
    }
}

/*
class FactsListUseCase(private val factsListService: FactsListService) {

    suspend fun getListOfFacts(): List<FactDetails> {
        val listOfFacts = factsListService.getListOfFacts()
        return listOfFacts?.map { fact ->
            FactDetails(
                imageUrl = fact.imageUrl.orEmpty(),
                title = fact.fact.orEmpty(),
                description = fact.description ?: "No description available",
                likeCount = 0,
                dislikeCount = 0,
                postedOn = fact.postedOn?.let { getFormattedDate(it) } ?: "Unknown date",
                postedBy = fact.postedBy ?: "Anonymous"
            )
        } ?: emptyList()
    }


    private fun getFormattedDate(inputDate: String): String {
        val today = Clock.System.todayIn(TimeZone.Companion.currentSystemDefault())
        val days = today.daysUntil(
            Instant.Companion.parse(inputDate).toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date
        )

        return when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }
    }
}*/
