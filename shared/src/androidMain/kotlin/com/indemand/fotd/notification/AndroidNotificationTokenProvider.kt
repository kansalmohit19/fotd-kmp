package com.indemand.fotd.notification

import android.content.Context
import com.google.firebase.FirebaseApp
import com.indemand.fotd.notification.provider.NotificationTokenProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidNotificationTokenProvider(
    context: Context,
) : NotificationTokenProvider {
    private var _notificationToken = MutableStateFlow<String?>(null)
    override val notificationToken = _notificationToken.asStateFlow()

    private val firebaseMessagingManager: FirebaseMessagingManager by lazy {
        FirebaseMessagingManager()
    }

    init {
        if (FirebaseApp.getApps(context).isNotEmpty()) {
            firebaseMessagingManager.getToken {
                _notificationToken.value = it
            }
        }
    }
}
