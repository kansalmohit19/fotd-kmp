package com.indemand.fotd.service

import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider
import com.indemand.fotd.analytics.provider.MParticleAnalyticsProvider

class AnalyticsServiceImpl(
    firebaseAnalyticsProvider: FirebaseAnalyticsProvider,
    mParticleAnalyticsProvider: MParticleAnalyticsProvider
) {

    val providers = listOf(firebaseAnalyticsProvider, mParticleAnalyticsProvider)


    fun initialise() {
        providers.forEach {
            it.initialize()
        }
    }
}