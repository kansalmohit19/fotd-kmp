package com.indemand.fotd

import android.os.Build

actual class Platform {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"

    actual fun logSystemInfo(): String {
        return "osName: $osName, osVersion: $osVersion, deviceModel: $deviceModel"
    }

    actual fun test(): String {
        TODO("Not yet implemented")
    }
}