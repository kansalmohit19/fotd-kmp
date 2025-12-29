package com.indemand.fotd.analytics.provider

abstract class FirebaseAnalyticsProvider : AnalyticsProvider() {
    abstract fun setUserId(userId: String)
    abstract fun setUserProperty(key: String, value: String)
    abstract fun log(log: String)
}