package com.indemand.fotd.analytics

import android.util.Log
import com.indemand.fotd.analytics.provider.MParticleAnalyticsProvider

class AndroidMParticleAnalyticsProvider : MParticleAnalyticsProvider() {

    override fun initialize() {
        //no operation
    }

    override fun logEvent(log: String) {
        Log.d("MParticle", "logEvent fun invoked log is $log")
    }
}