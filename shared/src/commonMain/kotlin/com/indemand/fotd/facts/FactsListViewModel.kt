package com.indemand.fotd.facts

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactsListViewModel : BaseViewModel() {

    private val _factsListFlow: MutableStateFlow<FactListState> =
        MutableStateFlow(FactListState(isLoading = true))
    val factsListFlow: StateFlow<FactListState> get() = _factsListFlow

    init {
        getFacts()
    }

    private fun getFacts() {
        scope.launch {
            delay(500)
            val listOfFacts = callApi()
            _factsListFlow.emit(FactListState(listOfFacts = listOfFacts))
        }
    }

    private fun callApi(): List<FactDetails> {
        return listOf()
    }
}