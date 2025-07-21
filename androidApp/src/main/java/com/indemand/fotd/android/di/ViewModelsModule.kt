package com.indemand.fotd.android.di

import com.indemand.fotd.facts.FactsListViewModel
import com.indemand.fotd.login.LoginViewModel
import com.indemand.fotd.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { FactsListViewModel(get()) }
}