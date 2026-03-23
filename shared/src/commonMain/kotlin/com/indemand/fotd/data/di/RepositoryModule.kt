package com.indemand.fotd.data.di

import com.indemand.fotd.data.repo.ConfigurationRepository
import com.indemand.fotd.data.repo.DailyFactRepository
import com.indemand.fotd.data.repo.FactsListRepository
import com.indemand.fotd.data.repo.GetAccessTokenRepo
import com.indemand.fotd.data.repo.LoginUserRepository
import com.indemand.fotd.data.repo.ValidateTokenRepo
import org.koin.dsl.module

val repositoryModule =
    module {
        single<ValidateTokenRepo> { ValidateTokenRepo(get()) }
        single<LoginUserRepository> { LoginUserRepository(get(), get()) }
        single<ConfigurationRepository> { ConfigurationRepository(get(), get(), get()) }
        single<DailyFactRepository> { DailyFactRepository(get(), get()) }
        single<FactsListRepository> { FactsListRepository(get(), get()) }
        single<GetAccessTokenRepo> { GetAccessTokenRepo(get()) }
    }
