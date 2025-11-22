package com.indemand.fotd.facts

import com.indemand.fotd.domain.model.FactDetails

data class FactListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val listOfFacts: List<FactDetails> = listOf()
)