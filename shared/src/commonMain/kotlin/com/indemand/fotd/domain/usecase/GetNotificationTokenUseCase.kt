package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import com.indemand.fotd.providers.NotificationTokenProvider

class GetNotificationTokenUseCase(val notificationTokenProvider: NotificationTokenProvider) :
    UseCase<Unit, String>() {

    override suspend fun run(params: Unit): Either<String, IFailure> {
        notificationTokenProvider.token.collect {
            println("============Token============$it")
        }

        return Either.Success("")
    }
}