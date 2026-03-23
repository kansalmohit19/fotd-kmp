package com.indemand.fotd.data.mapper

import com.indemand.fotd.data.model.UserDetailsDTO
import com.indemand.fotd.domain.model.UserDetails

fun UserDetailsDTO?.toDomain() =
    UserDetails(
        userId = this?.user_id ?: 0,
        rewardPoints = this?.reward_points ?: 0,
        name = this?.name.orEmpty(),
        email = this?.email.orEmpty(),
        isGuest = this?.is_guest ?: 0,
        accessToken = this?.access_token.orEmpty(),
        profileImage = this?.profile_image.orEmpty(),
        notificationEnabled = this?.notification_enabled ?: 0,
    )
