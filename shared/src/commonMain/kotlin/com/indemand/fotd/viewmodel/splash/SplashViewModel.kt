package com.indemand.fotd.viewmodel.splash

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.uistate.SplashUiState
import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val configurationUseCase: ConfigurationUseCase,
    private val appUpdateDialogUseCase: AppUpdateDialogUseCase
) : BaseViewModel() {
    private var isTimerStopped = false
    private var isAppVersionSuccess = false
    private var configurationDetails: ConfigurationDetails? = null
    private val _splashUIFlow: MutableStateFlow<SplashUiState> =
        MutableStateFlow(SplashUiState.Idle)
    val splashUIFlow: StateFlow<SplashUiState> get() = _splashUIFlow

    init {
        startTimer()
        checkAppVersion()
    }

    private fun startTimer() {
        scope.launch {
            delay(2000)
            isTimerStopped = true
            checkForAllProcesses()
        }
    }

    private fun checkAppVersion() {
        scope.launch {
            configurationUseCase.invoke(scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    configurationDetails = it
                    isAppVersionSuccess = true
                    checkForAllProcesses()
                },
                onFailure = {
                    println("Test: ${it.errorMessage}")
                })
        }
    }

    private fun checkForAllProcesses() {
        if (isTimerStopped && isAppVersionSuccess) {
            configurationDetails?.let {
                appUpdateDialogUseCase.invoke(
                    scope = CoroutineScope(Dispatchers.IO),
                    params = configurationDetails,
                    onSuccess = {
                        _splashUIFlow.value = it
                    },
                )
            } ?: run {
                _splashUIFlow.value = SplashUiState.ToHome
            }
        }
    }
}