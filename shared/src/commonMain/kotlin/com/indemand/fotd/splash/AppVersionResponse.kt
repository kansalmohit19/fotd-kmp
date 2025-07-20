package com.indemand.fotd.splash

import kotlinx.serialization.Serializable

@Serializable
data class AppVersionResponse(
    var is_force_update: Boolean?,
    val is_manual_update: Boolean?,
    val app_link: String?,
    val package_name: String?,
    val about_us_page: String?,
    val insta_handle: String?,
    val ad_mob_enabled: Int?
)