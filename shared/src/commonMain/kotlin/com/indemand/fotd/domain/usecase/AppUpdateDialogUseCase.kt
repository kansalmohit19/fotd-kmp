package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.AppVersionDetails
import com.indemand.fotd.domain.model.BottomSheetDetails
import com.indemand.fotd.domain.model.ButtonType
import com.indemand.fotd.domain.uistate.SplashUiState

class AppUpdateDialogUseCase : UseCase<AppVersionDetails?, SplashUiState>() {
    override suspend fun run(params: AppVersionDetails?): Either<SplashUiState, IFailure> {
        return if (params == null) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = "something went wrong!",
                        message = "Please reopen the app.",
                        isCancellable = false,
                        positiveButton = ButtonType.PositiveButton(text = "close")
                    )
                )
            )
        } else if (params.isForceUpdate) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = "time to update!",
                        message = "we have added a lot of new features for you.\nplease, update it to the latest version.",
                        isCancellable = false,
                        positiveButton = ButtonType.PositiveButton(
                            text = "update",
                            appPackageName = params.packageName,
                            appLink = params.appLink
                        )
                    )
                )
            )
        } else if (params.isManualUpdate) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = "update Available",
                        message = "a new version of the app is available. would you like to update?",
                        isCancellable = true,
                        negativeButton = ButtonType.NegativeButton(text = "later"),
                        positiveButton = ButtonType.PositiveButton(
                            text = "yes",
                            appPackageName = params.packageName,
                            appLink = params.appLink
                        )
                    )
                )
            )
        } else {
            Either.Success(SplashUiState.ToHome)
        }
    }
}