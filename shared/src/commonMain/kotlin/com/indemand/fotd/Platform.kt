package com.indemand.fotd


expect class Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String

    fun logSystemInfo(): String
}