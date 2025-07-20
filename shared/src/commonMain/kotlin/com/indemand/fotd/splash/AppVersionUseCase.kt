package com.indemand.fotd.splash

class AppVersionUseCase(private val appVersionRepository: AppVersionRepository) {
    suspend fun checkForAppVersion(): AppVersionDetails {
        val appVersionResponse = appVersionRepository.checkForAppVersion()
        return AppVersionDetails(
            isForceUpdate = appVersionResponse?.is_force_update ?: false,
            isManualUpdate = appVersionResponse?.is_manual_update ?: false,
            appLink = appVersionResponse?.app_link ?: "",
            packageName = appVersionResponse?.package_name ?: "",
            aboutUsPage = appVersionResponse?.about_us_page ?: "",
            instaHandle = appVersionResponse?.insta_handle ?: "",
            fbHandle = "",
            adMobEnabled = appVersionResponse?.ad_mob_enabled ?: 0
        )
    }
}