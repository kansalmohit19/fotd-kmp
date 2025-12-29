package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.domain.model.BottomSheetDetails
import com.indemand.fotd.domain.model.ButtonType
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.uistate.SplashUiState

class AppUpdateDialogUseCaseImpl : AppUpdateDialogUseCase {
    override suspend fun run(params: Pair<ConfigurationDetails, Int>): Either<SplashUiState, IFailure> {
        val configDetails = params.first
        val appVersionCode = params.second

        return if (isUpdateAvailable(configDetails.appUpdate.hardVersion, appVersionCode)) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = configDetails.appUpdate.hardUpdateTitle,
                        message = configDetails.appUpdate.hardUpdateMessage,
                        isCancellable = false,
                        positiveButton = ButtonType.PositiveButton(text = configDetails.appUpdate.hardUpdateButton),
                    ),
                    appLink = configDetails.appUpdate.appLink,
                    appPackageName = configDetails.appUpdate.packageName,
                )
            )
        } else if (isUpdateAvailable(configDetails.appUpdate.softVersion, appVersionCode)) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = configDetails.appUpdate.softUpdateTitle,
                        message = configDetails.appUpdate.softUpdateMessage,
                        isCancellable = true,
                        negativeButton = ButtonType.NegativeButton(text = configDetails.appUpdate.softUpdateNegativeButton),
                        positiveButton = ButtonType.PositiveButton(
                            text = configDetails.appUpdate.softUpdatePositiveButton,
                        )
                    ),
                    appLink = configDetails.appUpdate.appLink,
                    appPackageName = configDetails.appUpdate.packageName,
                )
            )
        } else {
            Either.Success(
                SplashUiState.AppUpdateDialog(null)
            )
        }
    }

    private fun isUpdateAvailable(versionCode: Int, appVersionCode: Int) =
        appVersionCode < versionCode
}