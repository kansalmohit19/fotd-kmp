package com.indemand.fotd.analytics

import co.touchlab.kermit.Logger

internal object AnalyticsLogger {
    var tag = "FOTDAnalytics"

    fun e(
        overrideTag: String? = null,
        message: String,
    ) {
        Logger.e(tag = overrideTag ?: tag, messageString = message)
    }

    fun d(
        overrideTag: String? = null,
        message: String,
    ) {
        Logger.d(tag = overrideTag ?: tag, messageString = message)
    }
}
