package com.indemand.fotd.domain.uistate

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object ToHome : LoginUiState
    data object ToForgotPassword : LoginUiState
    data object ToRegister : LoginUiState
    data class ShowError(val message: String) : LoginUiState
}