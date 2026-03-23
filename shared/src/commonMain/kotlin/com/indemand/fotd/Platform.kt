package com.indemand.fotd

expect object Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    public val appVersionCode: Int

    fun logSystemInfo(): String
}
