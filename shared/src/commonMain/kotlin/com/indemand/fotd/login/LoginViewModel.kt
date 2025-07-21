package com.indemand.fotd.login

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : BaseViewModel() {
    init {
        loginUser()
    }
    private fun loginUser() {
        println("LoginViewModel: loginUser called")
        scope.launch {
            val appVersionDetails = loginUserUseCase.loginUser()
        }
    }
}