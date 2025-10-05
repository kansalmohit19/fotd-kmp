package com.indemand.fotd.domain.di

import com.indemand.fotd.domain.usecase.AppUpdateDialogUseCase
import com.indemand.fotd.domain.usecase.AppVersionUseCase
import com.indemand.fotd.domain.usecase.LoginUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<AppVersionUseCase> { AppVersionUseCase(get()) }
    single<AppUpdateDialogUseCase> { AppUpdateDialogUseCase() }
    single<LoginUserUseCase> { LoginUserUseCase(get()) }
}