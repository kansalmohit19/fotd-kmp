package com.indemand.fotd.di

import com.indemand.fotd.analytics.di.analyticsModule
import com.indemand.fotd.data.di.remoteModule
import com.indemand.fotd.data.di.repositoryModule
import com.indemand.fotd.domain.di.useCaseModule

val sharedKoinModules = listOf(
    remoteModule,
    useCaseModule,
    repositoryModule,
    analyticsModule,
)