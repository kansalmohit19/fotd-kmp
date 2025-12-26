package com.indemand.fotd.notification

import com.indemand.fotd.notification.provider.NotificationTokenProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidNotificationTokenProvider() : NotificationTokenProvider {

    private var _notificationToken = MutableStateFlow<String?>(null)
    override val notificationToken = _notificationToken.asStateFlow()

    private val firebaseMessagingManager: FirebaseMessagingManager by lazy {
        FirebaseMessagingManager()
    }

    init {
        firebaseMessagingManager.getToken {
            _notificationToken.value = it
        }
    }
}


