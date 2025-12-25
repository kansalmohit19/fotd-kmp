package com.indemand.fotd.notification.provider

import kotlinx.coroutines.flow.StateFlow

interface NotificationTokenProvider {
    val notificationToken: StateFlow<String?>
}