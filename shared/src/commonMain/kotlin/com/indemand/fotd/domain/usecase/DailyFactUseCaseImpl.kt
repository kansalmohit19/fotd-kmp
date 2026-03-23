package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.Unknown
import com.indemand.fotd.data.repo.DailyFactRepository
import com.indemand.fotd.domain.model.FactDetails

class DailyFactUseCaseImpl(
    private val configurationUseCase: ConfigurationUseCase,
    private val dailyFactRepository: DailyFactRepository,
) : DailyFactUseCase {
    override suspend fun run(params: Unit): Either<FactDetails?, IFailure> {
        val accessToken = configurationUseCase.configuration?.tokenDetails?.accessToken
        return if (!accessToken.isNullOrEmpty()) {
            dailyFactRepository.getDailyFact(accessToken)
        } else {
            Either.Error(Unknown())
        }
    }
}
