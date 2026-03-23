package com.indemand.fotd

import platform.UIKit.UIDevice

actual object Platform {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model

    actual fun logSystemInfo(): String = "osName: $osName, osVersion: $osVersion, deviceModel: $deviceModel"

    actual val appVersionCode: Int
        get() = 1
}
