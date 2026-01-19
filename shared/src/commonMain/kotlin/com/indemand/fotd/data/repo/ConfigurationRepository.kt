package com.indemand.fotd.data.repo

import com.indemand.fotd.AssetDataSource
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.json
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
    val assetDataSource: AssetDataSource,
    val dataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) {
    private var internalConfiguration: MutableStateFlow<ConfigurationDetails?> =
        MutableStateFlow(null)
    val configuration: StateFlow<ConfigurationDetails?> get() = internalConfiguration
    private val CONFIG_PATH = "config.json"
    suspend fun fetchConfiguration(): Either<ConfigurationDetails, IFailure> {
        val result = if (true) {
            getLocal(
                input = json.decodeFromString(
                    ConfigurationDetailsDTO.serializer(),
                    assetDataSource.loadAssetFile(CONFIG_PATH),
                ), successTransform = {
                    it.toDomain()
                })
        } else {
            safeApiCall(
                //serializer = ConfigurationDetailsDTO.serializer(),
                apiCall = { dataSource.fetchConfiguration() }, successTransform = { it.toDomain() })
        }.also {
            internalConfiguration.value = it.successValue()
            saveToken()
        }
        return result
    }

    private suspend fun saveToken() {
        val tokenDetails = internalConfiguration.value?.tokenDetails
        if (tokenDetails?.useToken == true) {
            localDataSource.saveString(ACCESS_TOKEN, tokenDetails.accessToken)
        }
    }

    private fun <X, Y> getLocal(
        input: X, successTransform: (X?) -> Y
    ): Either<Y, IFailure> {
        return Either.Success(successTransform(input))
    }
}