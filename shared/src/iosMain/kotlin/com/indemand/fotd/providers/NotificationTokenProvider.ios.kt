package com.indemand.fotd.providers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class NotificationTokenProvider {
    actual val token: StateFlow<String?> = MutableStateFlow("")
}