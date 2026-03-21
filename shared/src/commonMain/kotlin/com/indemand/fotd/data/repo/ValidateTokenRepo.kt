package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.model.UserInfoDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.UserDetails

class ValidateTokenRepo(
    private val dataSource: UserApi,
) {
    suspend fun accessTokenLogin(request: LoginUserRequest): Either<UserDetails?, IFailure> {
        return safeApiCall(
            serializer = UserInfoDTO.serializer(),
        ) {
            dataSource.validateToken(request)
        }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.userInfo?.toDomain())
            } else {
                Either.Error(BackendFailure())
            }
        }
    }
}