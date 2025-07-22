package com.indemand.fotd.splash

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.usecase.AppVersionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val appVersionUseCase: AppVersionUseCase) : BaseViewModel() {
    private var isTimerStopped = false
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
            val appVersionDetails = appVersionUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    println("Test: Success")
                },
                onFailure = {
                    println("Test: ${it.errorMessage}")
                }
            )
            //Log.e("AppVersionDetails", appVersionDetails.toString())
            //_factsListFlow.emit(FactListState(listOfFacts = listOfFacts))
        }
    }

    fun checkForAllProcesses() {
        _splashUIFlow.value = SplashUiState.ToLogin
    }
}