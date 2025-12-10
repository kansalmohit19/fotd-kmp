package com.indemand.fotd.facts

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactsListViewModel(private val factsListUseCase: FactsListUseCase) : BaseViewModel() {

    private val _factsListFlow: MutableStateFlow<FactListState> =
        MutableStateFlow(FactListState.Idle)
    val factsListFlow: StateFlow<FactListState> get() = _factsListFlow

    init {
        getFacts()
    }

    private fun getFacts() {
        scope.launch {
            /*delay(1000)
            _factsListFlow.emit(FactListState(errorMessage = "Something went wrong, please try again."))
            delay(1000)
            val listOfFacts = callApi()*/

            val listOfFacts = factsListUseCase.getListOfFacts()
            _factsListFlow.emit(FactListState.ShowFacts(listOfFacts = listOfFacts))
        }
    }

    fun observeFacts(onUpdate: (FactListState) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            factsListFlow.collect { state ->
                onUpdate(state)
            }
        }
    }
}