package com.indemand.fotd.di

import com.indemand.fotd.facts.FactsListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin


fun initKoin() {
    val modules = sharedKoinModules

    startKoin {
        modules(modules)
    }
}

class FactsListInjector : KoinComponent {
    val factsListViewModel: FactsListViewModel by inject()
}