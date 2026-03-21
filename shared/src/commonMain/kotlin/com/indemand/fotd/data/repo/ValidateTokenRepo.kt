package com.indemand.fotd.data.repo

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import com.indemand.fotd.data.mapper.toDomain
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.model.UserInfoDTO
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.domain.model.UserDetails

class ValidateTokenRepo(
    private val dataSource: UserApi
) {
    suspend fun accessTokenLogin(request: LoginUserRequest): Either<UserDetails?, IFailure> {
        val result = safeApiCall(
            serializer = UserInfoDTO.serializer(),
            apiCall = { dataSource.validateToken(request) },
            successTransform = { it?.userInfo?.toDomain() })


        /*if (result is Either.Success) {
            keyValueStorage.putString("accessToken", result.successVal?.accessToken.orEmpty())
        }*/
        return result
    }
}