package com.indemand.fotd.di

import com.indemand.fotd.data.di.networkModule
import com.indemand.fotd.data.di.repositoryModule
import com.indemand.fotd.domain.di.useCaseModule
import com.indemand.fotd.facts.di.factsModule

val sharedKoinModules = listOf(
    networkModule,
    useCaseModule,
    repositoryModule,
    factsModule,
)