package com.indemand.fotd.analytics.provider

import kotlinx.coroutines.flow.MutableStateFlow

abstract class AnalyticsProvider {
    private var _initialized = MutableStateFlow<Boolean>(false)
    public val initialized: MutableStateFlow<Boolean> get() = _initialized

    public fun notifyInitialiseComplete() {
        _initialized.value = true
    }

    abstract fun initialize()
}
