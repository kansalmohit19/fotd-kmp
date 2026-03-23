package com.indemand.fotd.domain.model

data class AppUpdateDetails(
    var isForceUpdate: Boolean = false,
    var isManualUpdate: Boolean = false,
    var appLink: String = "",
    var packageName: String = "",
    var hardVersion: Int = 0,
    var softVersion: Int = 0,
    val softUpdateTitle: String = "",
    val softUpdateMessage: String = "",
    val softUpdatePositiveButton: String = "",
    val softUpdateNegativeButton: String = "",
    val hardUpdateTitle: String = "",
    val hardUpdateMessage: String = "",
    val hardUpdateButton: String = "",
)
