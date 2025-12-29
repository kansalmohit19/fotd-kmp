package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.UseCase
import com.indemand.fotd.data.model.LoginUserRequest
import com.indemand.fotd.domain.model.UserDetails

interface ValidateTokenUseCase : UseCase<LoginUserRequest, UserDetails?>