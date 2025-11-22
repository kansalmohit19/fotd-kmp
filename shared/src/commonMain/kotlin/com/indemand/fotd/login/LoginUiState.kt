package com.indemand.fotd.login

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object ToHome : LoginUiState
    data object ToForgotPassword : LoginUiState
    data object ToRegister : LoginUiState
    data class ShowError(val message: String) : LoginUiState
}
