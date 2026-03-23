package com.indemand.fotd.analytics

sealed interface AnalyticsEvent {
    data class ScreenView(
        val screenName: String,
    ) : AnalyticsEvent

    data class TabClick(
        val tabId: String,
    ) : AnalyticsEvent
}
