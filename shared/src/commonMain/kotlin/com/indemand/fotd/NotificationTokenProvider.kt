package com.indemand.fotd

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface NotificationTokenProvider {
    val notificationToken: StateFlow<String?>
}