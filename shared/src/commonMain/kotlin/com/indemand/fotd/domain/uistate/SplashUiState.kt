package com.indemand.fotd.domain.uistate

import com.indemand.fotd.domain.model.BottomSheetDetails

sealed interface SplashUiState {
    data object Idle : SplashUiState
    data object ToHome : SplashUiState
    data object ToLogin : SplashUiState
    data class AppUpdateDialog(val bottomSheetDetails: BottomSheetDetails) : SplashUiState
}