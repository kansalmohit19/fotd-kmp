package com.indemand.fotd.analytics.receiver

import com.indemand.fotd.analytics.provider.MParticleAnalyticsProvider

class MParticleAnalyticsReceiver(
    val mParticleAnalyticsProvider: MParticleAnalyticsProvider,
) : AnalyticsReceiver {
    override fun onPageView(screenName: String?) {
        screenName?.let { mParticleAnalyticsProvider.logEvent("screen: $it") }
    }

    override fun onTabClick(tabName: String?) {
        tabName?.let { mParticleAnalyticsProvider.logEvent("tab: $it") }
    }

    override fun onUserLogin(userId: String?) {
        userId?.let { mParticleAnalyticsProvider.logEvent("logged in: $it") }
    }

    override fun onUserLogout() {
        mParticleAnalyticsProvider.logEvent("logged out")
    }
}
