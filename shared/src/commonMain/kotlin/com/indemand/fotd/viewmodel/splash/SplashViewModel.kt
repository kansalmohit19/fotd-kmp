package com.indemand.fotd.viewmodel.splash

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.Platform
import com.indemand.fotd.analytics.AnalyticsAggregator
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.uistate.SplashUiState
import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCase
import com.indemand.fotd.domain.usecase.GetNotificationTokenUseCase
import com.indemand.fotd.domain.usecase.ValidateTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val configurationUseCase: ConfigurationUseCase,
    private val appUpdateDialogUseCase: AppUpdateDialogUseCase,
    private val getNotificationTokenUseCase: GetNotificationTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val validateTokenUseCase: ValidateTokenUseCase,
    private val analyticsAggregator: AnalyticsAggregator,
) : BaseViewModel() {
    private var isTimerStopped = false
    private var isFetchConfigSuccess = false
    private val _splashUIFlow: MutableStateFlow<SplashUiState> =
        MutableStateFlow(SplashUiState.Idle)
    val splashUIFlow: StateFlow<SplashUiState> get() = _splashUIFlow

    init {
        //startTimer()
        fetchAppConfig()
        analyticsAggregator.onTabClick("HOME")
        getFCMToken()
    }

    /*private fun startTimer() {
        scope.launch {
            delay(2000)
            isTimerStopped = true
            checkForAllProcesses()
        }
    }*/

    private fun fetchAppConfig() {/*scope.launch {
            getNotificationTokenUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    println("Success1: Token: ${it}")
                },
                onFailure = {
                    println("Test: ${it.errorMessage}")
                })
        }

        scope.launch {
            getNotificationTokenUseCase.token.collect {
                println("Success2: Token: ${it}")
            }
        }*/
        scope.launch {
            configurationUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    checkForAppUpdate(it)
                },
                onFailure = {
                    println("Error: ${it.errorMessage}")
                })
        }
    }

    private fun checkForAppUpdate(configurationDetails: ConfigurationDetails?) {
        configurationDetails?.let {
            scope.launch {
                appUpdateDialogUseCase.invoke(
                    scope = CoroutineScope(Dispatchers.IO),
                    params = configurationDetails to Platform.appVersionCode,
                    onSuccess = {
                        if (it is SplashUiState.AppUpdateDialog) {
                            if (it.bottomSheetDetails != null) {
                                _splashUIFlow.value = it
                            } else {
                                //check for next steps
                                checkAccessToken()
                            }
                        }
                    },
                    onFailure = {
                        println("Error: ${it.errorMessage}")
                    })
            }
        }
    }

    private fun checkAccessToken() {
        scope.launch {
            getAccessTokenUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    if (it.isNullOrEmpty()) {
                        _splashUIFlow.value = SplashUiState.NavigateToLogin
                    } else {
                        validateAccessToken(it)
                    }
                },
                onFailure = {
                    println("Error: ${it.errorMessage}")
                })
        }
    }

    private fun validateAccessToken(accessToken: String) {
        scope.launch {
            validateTokenUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = LoginUserRequest(accessToken = accessToken),
                onSuccess = {
                    _splashUIFlow.value = SplashUiState.NavigateToMain
                },
                onFailure = {
                    println("Error: ${it.errorMessage}")
                })
        }
    }

    private fun getFCMToken() {
        scope.launch {
            getNotificationTokenUseCase.token.collect { token ->
                println("====TOKEN==== $token")
            }
        }
    }
}