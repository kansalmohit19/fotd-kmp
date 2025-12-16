package com.indemand.fotd.domain.di

import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.DailyFactUseCase
import com.indemand.fotd.domain.usecase.FactsListUseCase
import com.indemand.fotd.domain.usecase.GetAccessTokenUseCase
import com.indemand.fotd.domain.usecase.GetNotificationTokenUseCase
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import com.indemand.fotd.domain.usecase.ValidateTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<AppUpdateDialogUseCase> { AppUpdateDialogUseCase() }
    single<ConfigurationUseCase> { ConfigurationUseCase(get(), get()) }
    single<DailyFactUseCase> { DailyFactUseCase(get(), get()) }
    single<FactsListUseCase> { FactsListUseCase(get(), get()) }
    single<GetAccessTokenUseCase> { GetAccessTokenUseCase(get()) }
    single<GetNotificationTokenUseCase> { GetNotificationTokenUseCase(get()) }
    single<LoginUserUseCase> { LoginUserUseCase(get()) }
    single<ValidateTokenUseCase> { ValidateTokenUseCase(get()) }
}