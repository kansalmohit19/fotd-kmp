package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.Unknown
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.LoginUserRepository
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.model.UserDetails

class LoginUserUseCase(private val loginUserRepository: LoginUserRepository) :
    UseCase<LoginUserRequest, UserDetails?>() {

    override suspend fun run(params: LoginUserRequest): Either<UserDetails?, IFailure> {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        if (emailRegex.matches(params.email).not()) {
            return Either.Error(Unknown("Please enter the valid email"))
        }
        if (params.password.length < 5) {
            return Either.Error(Unknown("Please enter the valid password"))
        }
        return loginUserRepository.loginUser(params)
    }
}