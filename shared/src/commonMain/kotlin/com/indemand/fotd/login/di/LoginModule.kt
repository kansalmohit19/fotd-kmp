package com.indemand.fotd.login.di

import com.indemand.fotd.login.LoginUserRepository
import com.indemand.fotd.login.LoginUserUseCase
import org.koin.dsl.module

val loginModule = module {

    single<LoginUserRepository> { LoginUserRepository(get()) }
    single<LoginUserUseCase> { LoginUserUseCase(get()) }
}