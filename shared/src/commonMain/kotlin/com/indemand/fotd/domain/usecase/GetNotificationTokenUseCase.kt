package com.indemand.fotd.domain.usecase

import kotlinx.coroutines.flow.StateFlow

interface GetNotificationTokenUseCase {
    val token: StateFlow<String?>
}