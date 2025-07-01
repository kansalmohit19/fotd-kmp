package com.indemand.fotd.facts

data class FactListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val listOfFacts: List<FactDetails> = listOf()
)