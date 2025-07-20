package com.indemand.fotd.splash

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private var isTimerStopped = false
    private val _splashUIFlow: MutableStateFlow<SplashUiState> =
        MutableStateFlow(SplashUiState.Idle)
    val splashUIFlow: StateFlow<SplashUiState> get() = _splashUIFlow

    init {
        startTimer()
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

    fun checkForAllProcesses() {
        _splashUIFlow.value = SplashUiState.ToHome
    }
}