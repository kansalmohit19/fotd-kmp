package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.ConfigurationDetails

interface ConfigurationUseCase : UseCase<Unit, ConfigurationDetails> {
    val configuration: ConfigurationDetails?
}