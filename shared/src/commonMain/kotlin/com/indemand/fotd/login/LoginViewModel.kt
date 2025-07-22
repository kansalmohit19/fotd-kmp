package com.indemand.fotd.login

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : BaseViewModel() {

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
                    println("Test: ${it.errorMessage}")
                })
        }
    }
}