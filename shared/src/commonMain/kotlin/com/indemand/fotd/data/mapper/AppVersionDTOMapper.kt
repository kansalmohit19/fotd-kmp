package com.indemand.fotd.data.mapper

import com.indemand.fotd.data.model.AppVersionDTO
import com.indemand.fotd.domain.model.AppVersionDetails

fun AppVersionDTO?.toDomain(): AppVersionDetails = AppVersionDetails(
    isForceUpdate = this?.is_force_update ?: false,
    isManualUpdate = this?.is_manual_update ?: false,
    appLink = this?.app_link ?: "",
    packageName = this?.package_name ?: "",
    aboutUsPage = this?.about_us_page ?: "",
    instaHandle = this?.insta_handle ?: "",
    fbHandle = "",
    adMobEnabled = this?.ad_mob_enabled ?: 0
)
