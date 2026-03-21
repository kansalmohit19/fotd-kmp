package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.DailyFactDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.FactDetails

class DailyFactRepository(private val dataSource: UserApi) {
    suspend fun getDailyFact(accessToken: String): Either<FactDetails?, IFailure> {
        return safeApiCall(
            serializer = DailyFactDTO.serializer(),
        ) { dataSource.dailyFact(accessToken) }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.data?.toDomain())
            } else {
                Either.Error(BackendFailure())
            }
        }
    }
}