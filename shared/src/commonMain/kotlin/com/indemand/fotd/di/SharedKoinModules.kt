package com.indemand.fotd.di

import com.indemand.fotd.data.di.networkModule
import com.indemand.fotd.data.di.repositoryModule
import com.indemand.fotd.domain.di.useCaseModule
import com.indemand.fotd.providers.di.providerModule

val sharedKoinModules = listOf(
    networkModule,
    useCaseModule,
    repositoryModule,
    providerModule
)