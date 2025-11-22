package com.indemand.fotd.data.di

import com.indemand.fotd.data.repo.AppVersionRepository
import com.indemand.fotd.data.repo.DailyFactRepository
import com.indemand.fotd.data.repo.LoginUserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AppVersionRepository> { AppVersionRepository(get()) }
    single<DailyFactRepository> { DailyFactRepository(get()) }
    single<LoginUserRepository> { LoginUserRepository(get()) }
}