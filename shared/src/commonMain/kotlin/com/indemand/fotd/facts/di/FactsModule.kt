package com.indemand.fotd.facts.di

import com.indemand.fotd.facts.FactsListService
import com.indemand.fotd.facts.FactsListUseCase
import com.indemand.fotd.facts.FactsListViewModel
import org.koin.dsl.module

val factsModule = module {

    single<FactsListService> { FactsListService(get()) }
    single<FactsListUseCase> { FactsListUseCase(get()) }
    single<FactsListViewModel> { FactsListViewModel(get()) }
}