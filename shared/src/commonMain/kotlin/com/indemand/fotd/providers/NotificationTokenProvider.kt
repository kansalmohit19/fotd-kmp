package com.indemand.fotd.providers

import kotlinx.coroutines.flow.StateFlow

expect class NotificationTokenProvider() {
    val token: StateFlow<String?>
}