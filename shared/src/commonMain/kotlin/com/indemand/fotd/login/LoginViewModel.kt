package com.indemand.fotd.login

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import com.indemand.fotd.splash.SplashUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : BaseViewModel() {

    private val _loginUIState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Idle)
    val loginUIState: StateFlow<LoginUiState> get() = _loginUIState

    fun loginUser(username: String, password: String) {
        println("LoginViewModel: loginUser called")
        scope.launch {
            loginUserUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = LoginUserRequest(username, password),
                onSuccess = {
                    println("Test: Success")
                },
                onFailure = {
                    _loginUIState.value = LoginUiState.ShowError(it.errorMessage)
                })
        }
    }
}