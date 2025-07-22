package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppVersionDTO(
    var is_force_update: Boolean?,
    val is_manual_update: Boolean?,
    val app_link: String?,
    val package_name: String?,
    val about_us_page: String?,
    val insta_handle: String?,
    val ad_mob_enabled: Int?
)