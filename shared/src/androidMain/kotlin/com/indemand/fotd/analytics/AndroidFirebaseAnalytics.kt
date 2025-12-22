package com.indemand.fotd.analytics

import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class AndroidFirebaseAnalytics : FirebaseAnalyticsProvider() {

    override fun initialize() {
        //no operation
    }

    override fun setUserId(userId: String?) {

    }

    override fun setUserProperty(key: String, value: String?) {

    }

    override fun log(log: String?) {

    }
}