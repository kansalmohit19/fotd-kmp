package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.BottomSheetDetails
import com.indemand.fotd.domain.model.ButtonType
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.uistate.SplashUiState
import io.ktor.util.logging.Logger

class AppUpdateDialogUseCase : UseCase<Pair<ConfigurationDetails?, Int>, SplashUiState>() {
    override suspend fun run(params: Pair<ConfigurationDetails?, Int>): Either<SplashUiState, IFailure> {
        val configDetails = params.first
        val appVersionCode = params.second
        println("")

        return if (configDetails == null) {
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
        } else if (isUpdateAvailable(configDetails.appUpdate.hardVersion, appVersionCode)) {
            Either.Success(
                SplashUiState.AppUpdateDialog(
                    BottomSheetDetails(
                        title = configDetails.appUpdate.hardUpdateTitle,
                        message = configDetails.appUpdate.hardUpdateMessage,
                        isCancellable = false,
                        positiveButton = ButtonType.PositiveButton(
                            text = configDetails.appUpdate.hardUpdateButton,
                            appPackageName = configDetails.appUpdate.packageName,
                            appLink = configDetails.appUpdate.appLink
                        )
                    )
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
                            appPackageName = configDetails.appUpdate.packageName,
                            appLink = configDetails.appUpdate.appLink
                        )
                    )
                )
            )
        } else {
            Either.Success(SplashUiState.ToHome)
        }
    }

    private fun isUpdateAvailable(versionCode: Int, appVersionCode: Int) =
        appVersionCode < versionCode
}