package com.indemand.fotd.facts.di

import com.indemand.fotd.di.networkModule

val sharedKoinModules = listOf(
    factsModule,
    networkModule
)