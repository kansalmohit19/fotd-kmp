package com.indemand.fotd.notification

import com.indemand.fotd.notification.provider.NotificationTokenProvider
import kotlinx.coroutines.flow.MutableStateFlow

class IOSNotificationTokenProvider : NotificationTokenProvider {
    override val notificationToken: MutableStateFlow<String?>
        get() {
            TODO()
        }
}
