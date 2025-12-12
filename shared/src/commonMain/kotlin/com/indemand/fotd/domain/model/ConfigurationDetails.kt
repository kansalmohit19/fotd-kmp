package com.indemand.fotd.domain.model

data class ConfigurationDetails(
    var appUpdate: AppUpdateDetails,
    var token: TokenDetails,
    var socialProfile: SocialProfileDetails
)