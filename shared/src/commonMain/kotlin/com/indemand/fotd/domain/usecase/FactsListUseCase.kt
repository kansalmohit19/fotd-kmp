package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.Unknown
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.FactsListRepository
import com.indemand.fotd.domain.model.FactDetails

class FactsListUseCase(
    private val configurationUseCase: ConfigurationUseCase,
    private val factsListRepository: FactsListRepository
) : UseCase<Unit, List<FactDetails>>() {

    override suspend fun run(params: Unit): Either<List<FactDetails>, IFailure> {
        val accessToken = configurationUseCase.configuration?.token?.accessToken
        return if (!accessToken.isNullOrEmpty()) {
            factsListRepository.getListOfFacts(accessToken)
        } else {
            Either.Error(Unknown())
        }
    }
}
