package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.GetAccessTokenRepo

class GetAccessTokenUseCase(private val getAccessTokenRepo: GetAccessTokenRepo) :
    UseCase<Unit, String?>() {
    override suspend fun run(params: Unit): Either<String?, IFailure> {
        return getAccessTokenRepo.getAccessToken()
    }
}