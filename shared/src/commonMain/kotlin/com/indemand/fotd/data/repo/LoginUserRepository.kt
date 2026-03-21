package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.model.UserInfoDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.UserDetails

class LoginUserRepository(
    private val localDataSource: LocalDataSource, private val dataSource: UserApi
) {
    suspend fun loginUser(request: LoginUserRequest): Either<UserDetails?, IFailure> {
        val result = safeApiCall(
            serializer = UserInfoDTO.serializer(),
            apiCall = { dataSource.loginUser(request) },
            successTransform = { it?.userInfo?.toDomain() })

        localDataSource.saveString("ACCESS_TOKEN", result.successValue()?.accessToken ?: "")
        return result
    }
}