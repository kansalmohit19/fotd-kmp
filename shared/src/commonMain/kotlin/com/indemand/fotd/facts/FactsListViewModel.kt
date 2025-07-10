package com.indemand.fotd.facts

import com.indemand.fotd.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
            delay(1000)
            _factsListFlow.emit(FactListState(errorMessage = "Something went wrong, please try again."))
            delay(1000)
            val listOfFacts = callApi()
            _factsListFlow.emit(FactListState(listOfFacts = listOfFacts))
        }
    }

    private fun callApi(): List<FactDetails> {
        return mutableListOf<FactDetails>().apply {
            add(
                FactDetails(
                    "https://placehold.co/1500x600/EFC3CA/FFFFFF/png",
                    "Fact Title - 1",
                    "Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1",
                    "12-Oct-2025"
                )
            )
            add(
                FactDetails(
                    "https://placehold.co/1500x600/FFECA1/FFFFFF/png",
                    "Fact Title - 2",
                    "Fact Description - 2 Fact Description - 2 Fact Description - 2 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1 Fact Description - 1",
                    "12-Nov-2025"
                )
            )
            add(
                FactDetails(
                    "https://placehold.co/1500x600.png",
                    "Fact Title - 3",
                    "Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 1",
                    "12-Dec-2025"
                )
            )
            add(
                FactDetails(
                    "https://placehold.co/1500x600.png",
                    "Fact Title - 4",
                    "Fact Description - 4 Fact Description - 4 Fact Description - 4 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 3 Fact Description - 1",
                    "12-Jan-2025"
                )
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