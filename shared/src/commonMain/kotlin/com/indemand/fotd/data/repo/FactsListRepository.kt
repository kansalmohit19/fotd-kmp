package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.FactsListDTO
import com.indemand.fotd.data.remote.DataSourceImpl
import com.indemand.fotd.domain.model.FactDetails

class FactsListRepository(private val dataSource: DataSourceImpl) {
    suspend fun getListOfFacts(accessToken: String): Either<List<FactDetails>, IFailure> {
        return safeApiCall(
            serializer = FactsListDTO.serializer(),
            apiCall = { dataSource.factsList(accessToken) },
            successTransform = { it.toDomain() })
    }
}