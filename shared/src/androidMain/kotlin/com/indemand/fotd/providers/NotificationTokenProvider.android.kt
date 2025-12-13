package com.indemand.fotd.providers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class NotificationTokenProvider {
    private var _token = MutableStateFlow("")
    actual val token: StateFlow<String?> get() = _token

    init {
        //init firebase
    }
}