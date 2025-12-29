package com.indemand.fotd.domain.usecase

import com.indemand.fotd.notification.provider.NotificationTokenProvider
import kotlinx.coroutines.flow.StateFlow

class GetNotificationTokenUseCaseImpl(notificationTokenProvider: NotificationTokenProvider) :
    GetNotificationTokenUseCase {
    override val token: StateFlow<String?> = notificationTokenProvider.notificationToken
}