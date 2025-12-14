package com.indemand.fotd.providers

import com.indemand.fotd.notification.FirebaseMessagingManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class NotificationTokenProvider {
    private var _token = MutableStateFlow<String?>(null)
    actual val token: StateFlow<String?> get() = _token
    val firebaseMessagingManager: FirebaseMessagingManager by lazy {
        FirebaseMessagingManager()
    }

    init {
        firebaseMessagingManager.getToken { token ->
            _token.value = token
        }
    }
}