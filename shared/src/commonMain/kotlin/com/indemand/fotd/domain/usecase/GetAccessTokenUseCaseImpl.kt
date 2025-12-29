package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.repo.GetAccessTokenRepo

class GetAccessTokenUseCaseImpl(private val getAccessTokenRepo: GetAccessTokenRepo) :
    GetAccessTokenUseCase {
    override suspend fun run(params: Unit): Either<String?, IFailure> {
        return getAccessTokenRepo.getAccessToken()
    }
}