package com.indemand.fotd.providers.di

import com.indemand.fotd.providers.NotificationTokenProvider
import org.koin.dsl.module

val providerModule = module {
    single<NotificationTokenProvider> { NotificationTokenProvider() }
}