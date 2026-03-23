package com.indemand.fotd.domain.usecase

import co.touchlab.kermit.Logger
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UserError
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.data.repo.LoginUserRepository
import com.indemand.fotd.domain.model.UserDetails
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class LoginUserUseCaseImpl(
    private val loginUserRepository: LoginUserRepository,
    private val notificationTokenUseCase: GetNotificationTokenUseCase,
) : LoginUserUseCase {
    override suspend fun run(params: LoginUserRequest): Either<UserDetails?, IFailure> {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        if (emailRegex.matches(params.email ?: "").not()) {
            return Either.Error(UserError(message = "Please enter the valid email"))
        }
        if ((params.password?.length ?: 0) < 5) {
            return Either.Error(UserError(message = "Please enter the valid password"))
        }
        notificationTokenUseCase.token.filterNotNull().first().also { token ->
            Logger.d(tag = "FCM", messageString = "TOKEN: $token")
            params.deviceToken = token
        }

        return loginUserRepository.loginUser(params)
    }
}
