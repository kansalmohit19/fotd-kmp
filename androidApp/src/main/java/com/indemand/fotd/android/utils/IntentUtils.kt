package com.indemand.fotd.android.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun openPlayStore(
    context: Context,
    appPackageName: String,
    appLink: String,
) {
    try {
        // Try to open Play Store app
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
        intent.setPackage("com.android.vending")
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // If Play Store app is not available, open in browser
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(appLink),
            )
        context.startActivity(intent)
    }
}
