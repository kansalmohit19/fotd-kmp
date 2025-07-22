package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.AppVersionDetails
import com.indemand.fotd.data.repo.AppVersionRepository

class AppVersionUseCase(private val appVersionRepository: AppVersionRepository) :
    UseCase<Unit, AppVersionDetails?>() {

    override suspend fun run(params: Unit): Either<AppVersionDetails?, IFailure> {
        return appVersionRepository.checkForAppVersion()
    }
}