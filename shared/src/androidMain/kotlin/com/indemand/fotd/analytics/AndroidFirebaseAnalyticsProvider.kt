package com.indemand.fotd.analytics

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.crashlytics
import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class AndroidFirebaseAnalyticsProvider(val context: Context) : FirebaseAnalyticsProvider() {

    override fun initialize() {
        if (FirebaseApp.getApps(context).isNotEmpty()) {
            notifyInitialiseComplete()
        }
    }

    override fun setUserId(userId: String) {
        Logger.d(message = "Firebase: setUserId fun invoked and userId is $userId")
        if (initialized.value) {
            Firebase.crashlytics.setUserId(userId)
        }
    }

    override fun setUserProperty(key: String, value: String) {
        Logger.d(message = "Firebase: setUserProperty fun invoked and key is $key, value is $value")
        if (initialized.value) {
            Firebase.crashlytics.setCustomKey(key, value)
        }
    }

    override fun log(log: String) {
        Logger.d(message = "Firebase: log fun invoked and log is $log")
        if (initialized.value) {
            Firebase.crashlytics.log(log)
        }
    }
}