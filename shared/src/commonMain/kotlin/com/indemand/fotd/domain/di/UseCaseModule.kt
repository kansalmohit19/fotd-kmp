package com.indemand.fotd.domain.di

import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.ConfigurationUseCase
import com.indemand.fotd.domain.usecase.DailyFactUseCase
import com.indemand.fotd.domain.usecase.FactsListUseCase
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<ConfigurationUseCase> { ConfigurationUseCase(get()) }
    single<AppUpdateDialogUseCase> { AppUpdateDialogUseCase() }
    single<LoginUserUseCase> { LoginUserUseCase(get()) }
    single<DailyFactUseCase> { DailyFactUseCase(get(), get()) }
    single<FactsListUseCase> { FactsListUseCase(get(), get()) }
}