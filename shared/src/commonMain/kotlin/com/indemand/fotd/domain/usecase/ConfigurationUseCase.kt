package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.repo.ConfigurationRepository
import com.indemand.fotd.domain.model.ConfigurationDetails

class ConfigurationUseCase(private val configurationRepository: ConfigurationRepository) :
    UseCase<Unit, ConfigurationDetails>() {

    val configuration: ConfigurationDetails? get() = configurationRepository.configuration.value

    override suspend fun run(params: Unit): Either<ConfigurationDetails, IFailure> {
        return configurationRepository.fetchConfiguration()
    }
}