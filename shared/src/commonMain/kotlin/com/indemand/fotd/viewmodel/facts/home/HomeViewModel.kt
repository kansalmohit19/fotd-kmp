package com.indemand.fotd.viewmodel.facts.home

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.domain.uistate.HomeUiState
import com.indemand.fotd.domain.usecase.DailyFactUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dailyFactUseCase: DailyFactUseCase,
    private val analyticsReceiver: AnalyticsReceiver,
) : BaseViewModel() {

    private val _homeFlow: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val factsListFlow: StateFlow<HomeUiState> get() = _homeFlow

    init {
        analyticsReceiver.onPageView("HOME")
        _homeFlow.value = HomeUiState.Loading
        getFacts()
    }

    private fun getFacts() {
        scope.launch {
            dailyFactUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    println("Success Response: ${it}")
                    it?.let { _homeFlow.value = HomeUiState.ShowFact(it) }
                },
                onFailure = {
                    _homeFlow.value = HomeUiState.Error(it.message.orEmpty())
                },
            )
        }
    }
}