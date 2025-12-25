package com.indemand.fotd.domain.usecase

import com.indemand.fotd.notification.provider.NotificationTokenProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class GetNotificationTokenUseCase(private val notificationTokenProvider: NotificationTokenProvider) {
    private var _token = MutableStateFlow<String?>(null)
    val token: Flow<String> = _token.filterNotNull().map { it }

    init {
        val token = notificationTokenProvider.notificationToken.filterNotNull().toString()
        _token.value = token
    }
}