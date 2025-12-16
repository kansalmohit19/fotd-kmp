package com.indemand.fotd.providers

import com.indemand.fotd.NotificationTokenProvider
import kotlinx.coroutines.flow.MutableStateFlow

class IOSNotificationTokenProvider : NotificationTokenProvider {
    override val notificationToken: MutableStateFlow<String?>
        get() {
            TODO()
        }

}