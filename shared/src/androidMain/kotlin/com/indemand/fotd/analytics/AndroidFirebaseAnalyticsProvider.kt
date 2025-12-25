package com.indemand.fotd.analytics

import android.util.Log
import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class AndroidFirebaseAnalyticsProvider : FirebaseAnalyticsProvider() {

    override fun initialize() {
        //no operation
    }

    override fun setUserId(userId: String?) {
        Log.d("Firebase", "setUserId fun invoked userId is $userId")
    }

    override fun setUserProperty(key: String, value: String?) {
        Log.d("Firebase", "setUserProperty fun invoked key is $key and $value is $value")
    }

    override fun log(log: String?) {
        Log.d("Firebase", "log fun invoked log is $log")
    }
}