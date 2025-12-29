package com.indemand.fotd.analytics

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class AndroidFirebaseAnalyticsProvider : FirebaseAnalyticsProvider() {

    override fun initialize() {
        //no operation
    }

    override fun setUserId(userId: String) {
        Logger.d("Firebase", "setUserId fun invoked and userId is $userId")
        Firebase.crashlytics.setUserId(userId)
    }

    override fun setUserProperty(key: String, value: String) {
        Logger.d(message = "setUserProperty fun invoked and key is $key, value is $value")
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun log(log: String) {
        Logger.d(message = "Firebase log fun invoked and log is $log")
        Firebase.crashlytics.log(log)
    }
}