package com.indemand.fotd.android.di

import com.indemand.fotd.viewmodel.facts.home.HomeViewModel
import com.indemand.fotd.viewmodel.facts.list.FactsListViewModel
import com.indemand.fotd.viewmodel.login.LoginViewModel
import com.indemand.fotd.viewmodel.main.MainViewModel
import com.indemand.fotd.viewmodel.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule =
    module {
        viewModelOf(::SplashViewModel)
        viewModelOf(::LoginViewModel)
        viewModelOf(::FactsListViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::HomeViewModel)
    }
