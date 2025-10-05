package com.indemand.fotd.splash

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.model.AppVersionDetails
import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.AppVersionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val appVersionUseCase: AppVersionUseCase,
    private val appUpdateDialogUseCase: AppUpdateDialogUseCase
) : BaseViewModel() {
    private var isTimerStopped = false
    private var isAppVersionSuccess = false
    private var appVersionDetails: AppVersionDetails? = null
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
        }/*if (!isTimerStopped) {
            Hand().postDelayed({
                isTimerStopped = true
                checkForAllProcesses()
            }, 1000)
        }*/
    }

    private fun checkAppVersion() {
        scope.launch {
            appVersionUseCase.invoke(scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    appVersionDetails = it
                    isAppVersionSuccess = true
                    checkForAllProcesses()
                },
                onFailure = {
                    println("Test: ${it.errorMessage}")
                })
            //Log.e("AppVersionDetails", appVersionDetails.toString())
            //_factsListFlow.emit(FactListState(listOfFacts = listOfFacts))
        }
    }

    private fun checkForAllProcesses() {
        if (isTimerStopped && isAppVersionSuccess) {
            appVersionDetails?.let {
                appUpdateDialogUseCase.invoke(
                    scope = CoroutineScope(Dispatchers.IO),
                    params = appVersionDetails,
                    onSuccess = {
                        _splashUIFlow.value = it
                    },
                )
            } ?: kotlin.run {
                _splashUIFlow.value = SplashUiState.ToHome
            }
        }
    }
}