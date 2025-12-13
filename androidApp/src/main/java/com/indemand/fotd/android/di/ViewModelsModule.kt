package com.indemand.fotd.android.di

import com.indemand.fotd.viewmodel.facts.home.HomeViewModel
import com.indemand.fotd.viewmodel.facts.list.FactsListViewModel
import com.indemand.fotd.viewmodel.login.LoginViewModel
import com.indemand.fotd.viewmodel.main.MainViewModel
import com.indemand.fotd.viewmodel.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule =
    module {
        viewModel { SplashViewModel(get(), get(), get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { FactsListViewModel(get()) }
        viewModel { MainViewModel() }
        viewModel { HomeViewModel(get()) }
    }
