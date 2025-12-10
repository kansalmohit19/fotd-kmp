package com.indemand.fotd.facts

import com.indemand.fotd.domain.model.FactDetails

sealed interface FactListState {
    data object Idle : FactListState
    data object Loading : FactListState
    data class Error(val errorMessage: String) : FactListState
    data class ShowFacts(val listOfFacts: List<FactDetails>) : FactListState
}