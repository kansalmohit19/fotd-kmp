package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.local.Constants.ACCESS_TOKEN
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.ConfigurationDetailsDTO
import com.indemand.fotd.data.remote.RemoteDataSource
import com.indemand.fotd.domain.model.ConfigurationDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ConfigurationRepository(
    private val dataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) {
    private var internalConfiguration: MutableStateFlow<ConfigurationDetails?> =
        MutableStateFlow(null)
    val configuration: StateFlow<ConfigurationDetails?> get() = internalConfiguration

    suspend fun fetchConfiguration(): Either<ConfigurationDetails, IFailure> {
        val result = safeApiCall(
            serializer = ConfigurationDetailsDTO.serializer(),
            apiCall = { dataSource.fetchConfiguration() },
            successTransform = { it.toDomain() })
        result.also {
            internalConfiguration.value = it.successValue()
            localDataSource.saveString(
                ACCESS_TOKEN, it.successValue()?.token?.accessToken.orEmpty()
            )
        }
        return result
    }
}