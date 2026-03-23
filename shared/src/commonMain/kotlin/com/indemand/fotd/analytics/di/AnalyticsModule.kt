package com.indemand.fotd.analytics.di

import com.indemand.fotd.analytics.AnalyticsAggregator
import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.analytics.receiver.FirebaseAnalyticsReceiver
import com.indemand.fotd.analytics.receiver.MParticleAnalyticsReceiver
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val analyticsModule =
    module {
        singleOf(::AnalyticsAggregator) { bind<AnalyticsReceiver>() }
        singleOf(::FirebaseAnalyticsReceiver)
        singleOf(::MParticleAnalyticsReceiver)
    }
