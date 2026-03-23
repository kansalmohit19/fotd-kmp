package com.indemand.fotd.viewmodel.facts.list

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.uistate.FactListState
import com.indemand.fotd.domain.usecase.FactsListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactsListViewModel(
    private val factsListUseCase: FactsListUseCase,
) : BaseViewModel() {
    private val _factsListFlow: MutableStateFlow<FactListState> =
        MutableStateFlow(FactListState.Idle)
    val factsListFlow: StateFlow<FactListState> get() = _factsListFlow

    init {
        getFacts()
    }

    private fun getFacts() {
        scope.launch {
            factsListUseCase.invoke(
                scope = CoroutineScope(Dispatchers.IO),
                params = Unit,
                onSuccess = {
                    println("Success Response: $it")
                    _factsListFlow.value = FactListState.ShowFacts(listOfFacts = it)
                },
                onFailure = {
                    println("Error: ${it.message}")
                    _factsListFlow.value = FactListState.Error(it.message.orEmpty())
                },
            )
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
