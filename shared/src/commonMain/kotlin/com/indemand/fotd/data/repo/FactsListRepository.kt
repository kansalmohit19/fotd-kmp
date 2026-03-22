package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.currentMillis
import com.indemand.fotd.data.local.CacheType
import com.indemand.fotd.data.local.ExpirableDataSourceImpl
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.FactsListDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.FactDetails
import kotlin.time.Duration.Companion.days

class FactsListRepository(val localDataSource: LocalDataSource, private val userApi: UserApi) {
    suspend fun getListOfFacts(accessToken: String): Either<List<FactDetails>, IFailure> {
        val url = "http://152.67.10.2:8080/fact/featured"
        return ExpirableDataSourceImpl(localDataSource).fetch(
            cacheableId = url,
            expiryTime = currentMillis() + 1.days.inWholeDays,
            cacheType = CacheType.USE_CACHE,
            serializer = FactsListDTO.serializer()
        ) {
            userApi.factsList(url, accessToken)
        }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.toDomain())
            } else {
                Either.Error(BackendFailure())
            }
        }
    }
}