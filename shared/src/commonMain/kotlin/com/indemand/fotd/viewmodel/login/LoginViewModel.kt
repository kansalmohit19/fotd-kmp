package com.indemand.fotd.viewmodel.login

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.uistate.LoginUiState
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val analyticsReceiver: AnalyticsReceiver,
) : BaseViewModel() {
    private val _loginUIState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Idle)
    val loginUIState: StateFlow<LoginUiState> get() = _loginUIState

    init {
        analyticsReceiver.onPageView("LOGIN")
    }

    fun loginUser(
        username: String,
        password: String,
    ) {
        println("LoginViewModel: loginUser called")
        scope.launch {
            loginUserUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = LoginUserRequest(username, password),
                onSuccess = {
                    _loginUIState.value = LoginUiState.ToHome
                },
                onFailure = {
                    _loginUIState.value = LoginUiState.ShowError(it.message.orEmpty())
                },
            )
        }
    }

    fun onForgotPassClick() {
        scope.launch {
            _loginUIState.value = LoginUiState.ToForgotPassword
            delay(100L)
            _loginUIState.value = LoginUiState.Idle
        }
    }
}
