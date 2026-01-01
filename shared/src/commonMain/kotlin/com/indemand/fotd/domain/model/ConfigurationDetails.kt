package com.indemand.fotd.domain.model

data class ConfigurationDetails(
    var appUpdate: AppUpdateDetails,
    var tokenDetails: TokenDetails,
    var socialProfile: SocialProfileDetails
)