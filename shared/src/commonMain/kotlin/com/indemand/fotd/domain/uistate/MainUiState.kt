package com.indemand.fotd.domain.uistate

sealed interface MainUiState {
    data object Idle : MainUiState
    data object Loading : MainUiState
    data object ShowHomeView : MainUiState
    data object ShowFactListView : MainUiState
    data object ShowMoreView : MainUiState
}