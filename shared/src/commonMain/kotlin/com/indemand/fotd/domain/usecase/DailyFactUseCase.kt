package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.DailyFactRepository
import com.indemand.fotd.domain.model.FactDetails

class DailyFactUseCase(private val dailyFactRepository: DailyFactRepository) :
    UseCase<Unit, FactDetails?>() {

    override suspend fun run(params: Unit): Either<FactDetails?, IFailure> {
        return dailyFactRepository.getDailyFact()
    }
}