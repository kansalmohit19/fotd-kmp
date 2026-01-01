package com.indemand.fotd.data.mapper

import com.indemand.fotd.data.model.AppUpdateDTO
import com.indemand.fotd.data.model.ConfigurationDetailsDTO
import com.indemand.fotd.data.model.SocialProfileDTO
import com.indemand.fotd.data.model.TokenDTO
import com.indemand.fotd.domain.model.AppUpdateDetails
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.model.SocialProfileDetails
import com.indemand.fotd.domain.model.TokenDetails

fun ConfigurationDetailsDTO?.toDomain(): ConfigurationDetails = ConfigurationDetails(
    appUpdate = this?.appUpdate?.toDomain() ?: AppUpdateDetails(),
    tokenDetails = this?.token?.toDomain() ?: TokenDetails(),
    socialProfile = this?.socialProfile?.toDomain() ?: SocialProfileDetails()
)

private fun AppUpdateDTO.toDomain() = AppUpdateDetails(
    isForceUpdate = this.isForceUpdate ?: false,
    isManualUpdate = this.isManualUpdate ?: false,
    appLink = this.appLink.orEmpty(),
    packageName = this.packageName.orEmpty(),
    hardVersion = this.hardVersion ?: 0,
    softVersion = this.softVersion ?: 0,
    softUpdateTitle = this.softUpdateTitle.orEmpty(),
    softUpdateMessage = this.softUpdateMessage.orEmpty(),
    softUpdatePositiveButton = this.softUpdatePositiveButton.orEmpty(),
    softUpdateNegativeButton = this.softUpdateNegativeButton.orEmpty(),
    hardUpdateTitle = this.hardUpdateTitle.orEmpty(),
    hardUpdateMessage = this.hardUpdateMessage.orEmpty(),
    hardUpdateButton = this.hardUpdateButton.orEmpty(),
)

private fun TokenDTO.toDomain() = TokenDetails(
    useToken = this.useToken ?: false,
    accessToken = this.accessToken.orEmpty()
)

private fun SocialProfileDTO.toDomain() = SocialProfileDetails(
    instaHandle = this.instaHandle.orEmpty(),
    aboutUsUrl = this.aboutUsUrl.orEmpty(),
    privacyPolicyUrl = this.privacyPolicyUrl.orEmpty()
)
