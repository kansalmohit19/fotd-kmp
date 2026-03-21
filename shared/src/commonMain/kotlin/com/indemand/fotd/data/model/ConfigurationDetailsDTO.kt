package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDTO(
    override val status: Int? = null, override val message: String? = null,
    var data: ConfigurationDataDTO? = null,
) : CommonResponse

@Serializable
data class ConfigurationDataDTO(
    var appUpdate: AppUpdateDTO? = null,
    var token: TokenDTO? = null,
    var socialProfile: SocialProfileDTO? = null,
)

@Serializable
data class AppUpdateDTO(
    var isForceUpdate: Boolean? = false,
    val isManualUpdate: Boolean? = false,
    val appLink: String? = "",
    val packageName: String? = "",
    val hardVersion: Int? = 0,
    val softVersion: Int? = 0,
    val softUpdateTitle: String? = "",
    val softUpdateMessage: String? = "",
    val softUpdatePositiveButton: String? = "",
    val softUpdateNegativeButton: String? = "",
    val hardUpdateTitle: String? = "",
    val hardUpdateMessage: String? = "",
    val hardUpdateButton: String? = "",
)

@Serializable
data class TokenDTO(
    var useToken: Boolean? = false, var accessToken: String? = "",
)

@Serializable
data class SocialProfileDTO(
    var instaHandle: String? = "", var aboutUsUrl: String? = "", var privacyPolicyUrl: String? = "",
)