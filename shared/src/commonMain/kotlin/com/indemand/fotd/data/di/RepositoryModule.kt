package com.indemand.fotd.data.di

import com.indemand.fotd.data.repo.ConfigurationRepository
import com.indemand.fotd.data.repo.DailyFactRepository
import com.indemand.fotd.data.repo.FactsListRepository
import com.indemand.fotd.data.repo.LoginUserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ConfigurationRepository> { ConfigurationRepository(get()) }
    single<DailyFactRepository> { DailyFactRepository(get()) }
    single<LoginUserRepository> { LoginUserRepository(get()) }
    single<FactsListRepository> { FactsListRepository(get()) }
}