package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.repo.ConfigurationRepository
import com.indemand.fotd.domain.model.ConfigurationDetails

class ConfigurationUseCaseImpl(
    private val localDataSource: LocalDataSource,
    private val configurationRepository: ConfigurationRepository
) : ConfigurationUseCase {

    override val configuration: ConfigurationDetails? get() = configurationRepository.configuration.value

    override suspend fun run(params: Unit): Either<ConfigurationDetails, IFailure> {
        println("======ACCESS_TOKEN=========${localDataSource.getString("ACCESS_TOKEN")}")
        return configurationRepository.fetchConfiguration()
    }
}