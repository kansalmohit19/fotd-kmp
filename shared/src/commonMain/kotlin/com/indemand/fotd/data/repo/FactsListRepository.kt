package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.FactsListDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.FactDetails

class FactsListRepository(private val dataSource: UserApi) {
    suspend fun getListOfFacts(accessToken: String): Either<List<FactDetails>, IFailure> {
        return safeApiCall(
            serializer = FactsListDTO.serializer(),
        ) {
            dataSource.factsList(accessToken)
        }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.toDomain())
            } else {
                Either.Error(BackendFailure())
            }
        }
    }
}