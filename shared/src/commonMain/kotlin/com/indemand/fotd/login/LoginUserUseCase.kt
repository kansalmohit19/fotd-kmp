package com.indemand.fotd.login

class LoginUserUseCase(private val loginUserRepository: LoginUserRepository) {
    suspend fun loginUser(): UserDetails {
        val response = loginUserRepository.loginUser()
        return UserDetails(
            userId = response?.userInfo?.user_id ?: 0,
            rewardPoints = response?.userInfo?.reward_points ?: 0,
            name = response?.userInfo?.name ?: "",
            email = response?.userInfo?.email ?: "",
            isGuest = response?.userInfo?.is_guest ?: 0,
            accessToken = response?.userInfo?.access_token ?: "",
            profileImage = response?.userInfo?.profile_image ?: "",
            notificationEnabled = response?.userInfo?.notification_enabled ?: 0
        )
    }
}