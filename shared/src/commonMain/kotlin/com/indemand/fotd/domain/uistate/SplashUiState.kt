package com.indemand.fotd.domain.uistate

import com.indemand.fotd.domain.model.BottomSheetDetails

sealed interface SplashUiState {
    data object Idle : SplashUiState

    data object NavigateToMain : SplashUiState

    data object NavigateToLogin : SplashUiState

    data class AppUpdateDialog(
        val bottomSheetDetails: BottomSheetDetails? = null,
        val appLink: String? = "",
        val appPackageName: String? = "",
    ) : SplashUiState
}
