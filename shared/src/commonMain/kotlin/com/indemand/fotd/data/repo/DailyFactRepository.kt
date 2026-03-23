package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.currentMillis
import com.indemand.fotd.data.local.CacheType
import com.indemand.fotd.data.local.ExpirableDataSourceImpl
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.DailyFactDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.FactDetails
import kotlin.time.Duration.Companion.hours

class DailyFactRepository(
    private val userApi: UserApi,
    val localDataSource: LocalDataSource,
) {
    suspend fun getDailyFact(accessToken: String): Either<FactDetails?, IFailure> {
        val url = "http://152.67.10.2:8080/fact/today"
        return ExpirableDataSourceImpl(localDataSource)
            .fetch(
                cacheableId = url,
                expiryTime = currentMillis() + 6.hours.inWholeMilliseconds,
                cacheType = CacheType.USE_CACHE,
                serializer = DailyFactDTO.serializer(),
            ) {
                userApi.dailyFact(url, accessToken)
            }.flatMap { response ->
                if (response.status == 200) {
                    Either.Success(response.data?.toDomain())
                } else {
                    Either.Error(BackendFailure())
                }
            }
    }
}
