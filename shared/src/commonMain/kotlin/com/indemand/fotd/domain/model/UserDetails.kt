package com.indemand.fotd.domain.model

data class UserDetails(
    var userId: Int,
    val rewardPoints: Int,
    val name: String,
    val email: String,
    val isGuest: Int,
    val accessToken: String,
    val profileImage: String,
    val notificationEnabled: Int,
)
