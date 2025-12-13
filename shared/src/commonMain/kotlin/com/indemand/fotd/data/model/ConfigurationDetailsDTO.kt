package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDetailsDTO(
    var appUpdate: AppUpdateDTO?,
    var token: TokenDTO?,
    var socialProfile: SocialProfileDTO?,
)

@Serializable
data class AppUpdateDTO(
    var isForceUpdate: Boolean?,
    val isManualUpdate: Boolean?,
    val appLink: String?,
    val packageName: String?,
    val hardVersion: Int?,
    val softVersion: Int?,
    val softUpdateTitle: String?,
    val softUpdateMessage: String?,
    val softUpdatePositiveButton: String?,
    val softUpdateNegativeButton: String?,
    val hardUpdateTitle: String?,
    val hardUpdateMessage: String?,
    val hardUpdateButton: String?,
)

@Serializable
data class TokenDTO(
    var accessToken: String?
)

@Serializable
data class SocialProfileDTO(
    var instaHandle: String?, var aboutUsUrl: String?, var privacyPolicyUrl: String?
)