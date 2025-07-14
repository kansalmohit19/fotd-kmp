package com.indemand.fotd.facts

class FactsListUseCase(private val factsListService: FactsListService) {

    suspend fun getListOfFacts(): List<FactDetails> {
        val listOfFacts = factsListService.getListOfFacts()
        return listOfFacts?.map { fact ->
            FactDetails(
                imageUrl = fact.imageUrl,
                titleText = fact.title,
                descriptionText = fact.description ?: "No description available",
                postedOnDate = fact.postedOn ?: "Unknown date",
                postedBy = fact.postedBy ?: "Anonymous"
            )
        } ?: emptyList()
    }
}