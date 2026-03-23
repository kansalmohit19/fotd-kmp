package com.indemand.fotd.data.repo

import com.indemand.fotd.core.BackendFailure
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.LoginInfoDTO
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.UserDetails

class ValidateTokenRepo(
    private val dataSource: UserApi,
) {
    suspend fun accessTokenLogin(request: LoginUserRequest): Either<UserDetails?, IFailure> =
        safeApiCall(
            serializer = LoginInfoDTO.serializer(),
        ) {
            dataSource.validateToken(request)
        }.flatMap { response ->
            if (response.status == 200) {
                Either.Success(response.data?.userInfo?.toDomain())
            } else {
                Either.Error(BackendFailure())
            }
        }
}
