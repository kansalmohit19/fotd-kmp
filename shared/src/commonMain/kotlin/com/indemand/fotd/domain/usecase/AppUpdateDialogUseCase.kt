package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.ConfigurationDetails
import com.indemand.fotd.domain.uistate.SplashUiState

interface AppUpdateDialogUseCase : UseCase<Pair<ConfigurationDetails, Int>, SplashUiState>
