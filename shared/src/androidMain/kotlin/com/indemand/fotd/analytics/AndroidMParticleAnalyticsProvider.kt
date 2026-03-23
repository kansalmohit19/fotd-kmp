package com.indemand.fotd.analytics

import com.indemand.fotd.analytics.provider.MParticleAnalyticsProvider

class AndroidMParticleAnalyticsProvider : MParticleAnalyticsProvider() {
    override fun initialize() {
        // no operation
    }

    override fun logEvent(log: String) {
        AnalyticsLogger.d(message = "MParticle: log fun invoked and log is $log")
    }
}
