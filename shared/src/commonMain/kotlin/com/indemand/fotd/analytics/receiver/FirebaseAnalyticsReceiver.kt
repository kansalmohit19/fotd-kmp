package com.indemand.fotd.analytics.receiver

import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class FirebaseAnalyticsReceiver(val firebaseAnalyticsProvider: FirebaseAnalyticsProvider) :
    AnalyticsReceiver {

    override fun onPageView(screenName: String?) {
        firebaseAnalyticsProvider.log("screen: $screenName")
    }

    override fun onTabClick(tabName: String?) {
        firebaseAnalyticsProvider.log("tab: $tabName")
    }

    override fun onUserLogin(userId: String?) {
        firebaseAnalyticsProvider.log("logged in: $userId")
        firebaseAnalyticsProvider.setUserId(userId)
    }

    override fun onUserLogout() {
        firebaseAnalyticsProvider.log("logged out")
        firebaseAnalyticsProvider.setUserId("")
    }
}