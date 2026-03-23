package com.indemand.fotd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDTO(
    var appUpdate: AppUpdateDTO? = null,
    var token: TokenDTO? = null,
    var socialProfile: SocialProfileDTO? = null,
    var addFact: AddFactDTO? = null,
)

@Serializable
data class AppUpdateDTO(
    var isForceUpdate: Boolean? = null,
    val isManualUpdate: Boolean? = null,
    val appLink: String? = null,
    val packageName: String? = null,
    val hardVersion: Int? = null,
    val softVersion: Int? = null,
    val softUpdateTitle: String? = null,
    val softUpdateMessage: String? = null,
    val softUpdatePositiveButton: String? = null,
    val softUpdateNegativeButton: String? = null,
    val hardUpdateTitle: String? = null,
    val hardUpdateMessage: String? = null,
    val hardUpdateButton: String? = null,
)

@Serializable
data class TokenDTO(
    var useToken: Boolean? = null,
    var accessToken: String? = null,
)

@Serializable
data class SocialProfileDTO(
    var instaHandle: String? = null,
    var aboutUsUrl: String? = null,
    var privacyPolicyUrl: String? = null,
)

@Serializable
data class AddFactDTO(
    val enabled: Boolean? = null,
)
