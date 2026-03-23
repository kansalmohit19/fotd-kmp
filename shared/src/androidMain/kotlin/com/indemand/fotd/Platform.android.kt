package com.indemand.fotd

import android.os.Build

actual object Platform {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"

    actual fun logSystemInfo(): String = "osName: $osName, osVersion: $osVersion, deviceModel: $deviceModel"

    actual val appVersionCode: Int
        get() = BuildConfig.VERSION_CODE
}
