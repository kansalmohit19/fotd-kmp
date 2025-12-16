package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.repo.ValidateTokenRepo
import com.indemand.fotd.domain.model.UserDetails

class ValidateTokenUseCase(private val validateTokenRepo: ValidateTokenRepo) :
    UseCase<LoginUserRequest, UserDetails?>() {
    override suspend fun run(params: LoginUserRequest): Either<UserDetails?, IFailure> {
        return validateTokenRepo.accessTokenLogin(params)
    }
}