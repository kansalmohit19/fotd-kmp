package com.indemand.fotd.domain.usecase

import com.indemand.fotd.NotificationTokenProvider
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GetNotificationTokenUseCase(val notificationTokenProvider: NotificationTokenProvider) :
    UseCase<Unit, String>() {
    private var _token = MutableStateFlow<String?>(null)
    val token: Flow<String> = _token.filterNotNull().map { it }

    override suspend fun run(params: Unit): Either<String, IFailure> {
        val token = notificationTokenProvider.notificationToken.filterNotNull().first()
        _token.value = notificationTokenProvider.notificationToken.filterNotNull().first()
        return Either.Success(token)
    }
}