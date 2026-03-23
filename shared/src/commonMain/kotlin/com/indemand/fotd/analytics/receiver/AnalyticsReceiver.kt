package com.indemand.fotd.analytics.receiver

interface AnalyticsReceiver {
    fun onPageView(screenName: String?)

    fun onTabClick(tabName: String?)

    fun onUserLogin(userId: String?)

    fun onUserLogout()
}
