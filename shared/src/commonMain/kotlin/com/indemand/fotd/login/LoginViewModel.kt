package com.indemand.fotd.login

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : BaseViewModel() {

    fun loginUser(username: String, password: String) {
        println("LoginViewModel: loginUser called")
        scope.launch {
            val appVersionDetails = loginUserUseCase.loginUser(username, password)
        }
    }
}