package com.indemand.fotd.analytics

import co.touchlab.kermit.Logger

internal object Logger {
    var TAG = "FOTDAnalytics"

    fun e(overrideTag: String? = null, message: String) {
        Logger.e(tag = overrideTag ?: TAG, messageString = message)
    }

    fun d(overrideTag: String? = null, message: String) {
        Logger.d(tag = overrideTag ?: TAG, messageString = message)
    }
}