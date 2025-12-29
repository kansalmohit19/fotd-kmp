package com.indemand.fotd.domain.di

import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCaseImpl
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCaseImpl
import com.indemand.fotd.domain.usecase.DailyFactUseCase
import com.indemand.fotd.domain.usecase.DailyFactUseCaseImpl
import com.indemand.fotd.domain.usecase.FactsListUseCase
import com.indemand.fotd.domain.usecase.FactsListUseCaseImpl
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCase
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCaseImpl
import com.indemand.fotd.domain.usecase.GetNotificationTokenUseCase
import com.indemand.fotd.domain.usecase.GetNotificationTokenUseCaseImpl
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import com.indemand.fotd.domain.usecase.LoginUserUseCaseImpl
import com.indemand.fotd.domain.usecase.ValidateTokenUseCase
import com.indemand.fotd.domain.usecase.ValidateTokenUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::AppUpdateDialogUseCaseImpl) { bind<AppUpdateDialogUseCase>() }
    singleOf(::ConfigurationUseCaseImpl) { bind<ConfigurationUseCase>() }
    singleOf(::DailyFactUseCaseImpl) { bind<DailyFactUseCase>() }
    singleOf(::FactsListUseCaseImpl) { bind<FactsListUseCase>() }
    singleOf(::GetAccessTokenUseCaseImpl) { bind<GetAccessTokenUseCase>() }
    singleOf(::GetNotificationTokenUseCaseImpl) { bind<GetNotificationTokenUseCase>() }
    singleOf(::LoginUserUseCaseImpl) { bind<LoginUserUseCase>() }
    singleOf(::ValidateTokenUseCaseImpl) { bind<ValidateTokenUseCase>() }
}