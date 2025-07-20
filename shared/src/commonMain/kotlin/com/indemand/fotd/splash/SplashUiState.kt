package com.indemand.fotd.splash

sealed interface SplashUiState {
    data object Idle : SplashUiState
    data object ToHome : SplashUiState
    data object ToLogin : SplashUiState
    data class OpenPlaystore(val packageName: String, val link: String) : SplashUiState
}
