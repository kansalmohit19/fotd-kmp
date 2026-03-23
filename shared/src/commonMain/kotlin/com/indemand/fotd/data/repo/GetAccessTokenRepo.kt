package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.local.Constants.ACCESS_TOKEN
import com.indemand.fotd.data.local.LocalDataSource

class GetAccessTokenRepo(
    private val dataSource: LocalDataSource,
) {
    suspend fun getAccessToken(): Either<String?, IFailure> = Either.Success(dataSource.getString(ACCESS_TOKEN))
}
