package com.indemand.fotd.facts

data class FactListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val listOfFacts: List<FactDetails> = listOf()
)