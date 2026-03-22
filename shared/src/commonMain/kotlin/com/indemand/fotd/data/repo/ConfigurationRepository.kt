package com.indemand.fotd.data.repo

import com.indemand.fotd.AssetDataSource
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.currentMillis
import com.indemand.fotd.data.local.CacheType
import com.indemand.fotd.data.local.Constants.ACCESS_TOKEN
import com.indemand.fotd.data.local.ExpirableDataSourceImpl
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.ConfigurationDTO
import com.indemand.fotd.data.remote.ConfigApi
import com.indemand.fotd.domain.model.ConfigurationDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Duration.Companion.hours

class ConfigurationRepository(
    val configApi: ConfigApi,
    val localDataSource: LocalDataSource,
    val assetDataSource: AssetDataSource,
) {
    private var internalConfiguration: MutableStateFlow<ConfigurationDetails?> =
        MutableStateFlow(null)
    val configuration: StateFlow<ConfigurationDetails?> get() = internalConfiguration

    suspend fun fetchConfiguration(): Either<ConfigurationDetails, IFailure> {
        val url =
            "https://raw.githubusercontent.com/kansalmohit19/configs/refs/heads/master/releases/master-2.1.1-config.json"
        val result = ExpirableDataSourceImpl(localDataSource).fetch(
            cacheableId = url,
            expiryTime = currentMillis() + 1.hours.inWholeMilliseconds,
            cacheType = CacheType.USE_CACHE,
            serializer = ConfigurationDTO.serializer(),
        ) {
            configApi.fetchConfiguration(url)
        }.flatMap { response ->
            Either.Success(response.toDomain())
        }

        result.also {
            if (it.isSuccess) {
                internalConfiguration.value = it.successValue()
                saveToken()
            }
        }
        return result
    }

    private suspend fun saveToken() {
        val tokenDetails = internalConfiguration.value?.tokenDetails
        if (tokenDetails?.useToken == true) {
            localDataSource.saveString(ACCESS_TOKEN, tokenDetails.accessToken)
        }
    }
}