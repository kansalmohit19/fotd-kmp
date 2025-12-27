package com.indemand.fotd.analytics.receiver

import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider

class FirebaseAnalyticsReceiver(val firebaseAnalyticsProvider: FirebaseAnalyticsProvider) :
    AnalyticsReceiver {

    override fun onPageView(screenName: String?) {
        firebaseAnalyticsProvider.log("PageViewed: $screenName")
    }

    override fun onTabClick(tabName: String?) {
        firebaseAnalyticsProvider.log("TabClicked: $tabName")
        tabName?.let {
            firebaseAnalyticsProvider.setUserProperty(
                key = "TabClicked", value = tabName
            )
        }
    }

    override fun onUserLogin(userId: String?) {
        userId?.let {
            firebaseAnalyticsProvider.log("UserLoggedIn: $it")
            firebaseAnalyticsProvider.setUserId(it)
        }
    }

    override fun onUserLogout() {
        firebaseAnalyticsProvider.log("UserLoggedOut")
        firebaseAnalyticsProvider.setUserId("")
    }
}