package com.indemand.fotd.analytics.provider

abstract class MParticleAnalyticsProvider : AnalyticsProvider() {
    abstract fun logEvent(log: String)
}