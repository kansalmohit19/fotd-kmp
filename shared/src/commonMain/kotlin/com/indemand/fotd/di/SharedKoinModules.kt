package com.indemand.fotd.di

import com.indemand.fotd.facts.di.factsModule
import com.indemand.fotd.login.di.loginModule
import com.indemand.fotd.splash.di.splashModule

val sharedKoinModules = listOf(
    networkModule,
    splashModule,
    loginModule,
    factsModule,
)