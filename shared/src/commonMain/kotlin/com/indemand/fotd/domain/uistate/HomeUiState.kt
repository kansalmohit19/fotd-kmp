package com.indemand.fotd.domain.uistate

import com.indemand.fotd.domain.model.FactDetails

sealed interface HomeUiState {
    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(
        val errorMessage: String,
    ) : HomeUiState

    data class ShowFact(
        val factDetails: FactDetails,
    ) : HomeUiState
}
