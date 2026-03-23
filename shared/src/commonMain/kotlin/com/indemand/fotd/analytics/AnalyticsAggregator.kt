package com.indemand.fotd.analytics

import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.analytics.receiver.FirebaseAnalyticsReceiver
import com.indemand.fotd.analytics.receiver.MParticleAnalyticsReceiver

class AnalyticsAggregator(
    firebaseAnalyticsReceiver: FirebaseAnalyticsReceiver,
    mParticleAnalyticsReceiver: MParticleAnalyticsReceiver,
) : AnalyticsReceiver {
    val receivers = listOf(firebaseAnalyticsReceiver, mParticleAnalyticsReceiver)

    override fun onPageView(screenName: String?) {
        receivers.forEach {
            it.onPageView(screenName)
        }
    }

    override fun onTabClick(tabName: String?) {
        receivers.forEach {
            it.onTabClick(tabName)
        }
    }

    override fun onUserLogin(userId: String?) {
        receivers.forEach {
            it.onUserLogin(userId)
        }
    }

    override fun onUserLogout() {
        receivers.forEach {
            it.onUserLogout()
        }
    }
}
