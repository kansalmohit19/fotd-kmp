package com.indemand.fotd

import platform.UIKit.UIDevice

actual class Platform {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model

    actual fun logSystemInfo(): String {
        return "osName: $osName, osVersion: $osVersion, deviceModel: $deviceModel"
    }

    actual fun test(): String {
        TODO("Not yet implemented")
    }
}