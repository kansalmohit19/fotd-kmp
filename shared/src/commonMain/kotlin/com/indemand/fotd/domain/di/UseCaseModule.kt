package com.indemand.fotd.domain.di

import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.DailyFactUseCase
import com.indemand.fotd.domain.usecase.FactsListUseCase
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCase
import com.indemand.fotd.domain.usecase.GetNotificationTokenUseCase
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import com.indemand.fotd.domain.usecase.ValidateTokenUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::AppUpdateDialogUseCase)
    singleOf(::ConfigurationUseCase)
    singleOf(::DailyFactUseCase)
    singleOf(::FactsListUseCase)
    singleOf(::GetAccessTokenUseCase)
    singleOf(::GetNotificationTokenUseCase)
    singleOf(::LoginUserUseCase)
    singleOf(::ValidateTokenUseCase)
}