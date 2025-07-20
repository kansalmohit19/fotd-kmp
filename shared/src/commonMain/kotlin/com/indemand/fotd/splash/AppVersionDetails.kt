package com.indemand.fotd.splash

data class AppVersionDetails(
    var isForceUpdate: Boolean,
    val isManualUpdate: Boolean,
    val appLink: String,
    val packageName: String,
    val aboutUsPage: String,
    val instaHandle: String,
    val fbHandle: String,
    val adMobEnabled: Int
)