package com.indemand.fotd.splash.di

import com.indemand.fotd.splash.AppVersionRepository
import com.indemand.fotd.splash.AppVersionUseCase
import org.koin.dsl.module

val splashModule = module {

    single<AppVersionRepository> { AppVersionRepository(get()) }
    single<AppVersionUseCase> { AppVersionUseCase(get()) }
}