package com.indemand.fotd.domain.model

data class AppUpdateDetails(
    var isForceUpdate: Boolean = false,
    var isManualUpdate: Boolean = false,
    var appLink: String = "",
    var packageName: String = "",
    var hardVersion: Long = 0L,
    var softVersion: Long = 0L
)