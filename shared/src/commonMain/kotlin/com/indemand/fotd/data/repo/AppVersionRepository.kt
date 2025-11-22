package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.AppVersionDTO
import com.indemand.fotd.data.model.AppVersionRequest
import com.indemand.fotd.data.remote.DataSourceImpl
import com.indemand.fotd.domain.model.AppVersionDetails

class AppVersionRepository(private val dataSource: DataSourceImpl) {
    suspend fun checkForAppVersion(): Either<AppVersionDetails?, IFailure> {
        return safeApiCall(
            serializer = AppVersionDTO.serializer(),
            apiCall = { dataSource.checkForAppVersion(AppVersionRequest()) },
            successTransform = { it.toDomain() })
    }
}