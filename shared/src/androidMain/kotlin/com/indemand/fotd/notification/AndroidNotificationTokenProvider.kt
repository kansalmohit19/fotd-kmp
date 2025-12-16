package com.indemand.fotd.notification

import com.indemand.fotd.NotificationTokenProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AndroidNotificationTokenProvider() : NotificationTokenProvider {

    private var _notificationToken = MutableStateFlow<String?>(null)
    override val notificationToken: StateFlow<String?>
        get() = _notificationToken

    private val firebaseMessagingManager: FirebaseMessagingManager by lazy {
        FirebaseMessagingManager()
    }

    init {
        firebaseMessagingManager.getToken {
            _notificationToken.value = it
        }
    }
}


