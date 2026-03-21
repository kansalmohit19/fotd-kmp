package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.local.Constants.ACCESS_TOKEN
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.ConfigurationDTO
import com.indemand.fotd.data.remote.ConfigApi
import com.indemand.fotd.domain.model.ConfigurationDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfigurationRepository(
    val configApi: ConfigApi, val localDataSource: LocalDataSource,
) {
    private var internalConfiguration: MutableStateFlow<ConfigurationDetails?> = MutableStateFlow(null)
    val configuration: StateFlow<ConfigurationDetails?> get() = internalConfiguration

    suspend fun fetchConfiguration(): Either<ConfigurationDetails, IFailure> {
        val result = safeApiCall(
            serializer = ConfigurationDTO.serializer(),
        ) {
            configApi.fetchConfiguration()
        }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.data!!.toDomain())
            } else {
                Either.Error(
                    BackendFailure(
                        message = response.message,
                    ),
                )

            }
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