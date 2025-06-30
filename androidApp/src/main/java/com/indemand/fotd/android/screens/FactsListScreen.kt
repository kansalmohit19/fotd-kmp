package com.indemand.fotd.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.indemand.fotd.facts.FactsListViewModel

@Composable
fun FactsListScreen(
    factsListViewModel: FactsListViewModel
) {

    val factsState = factsListViewModel.factsListFlow.collectAsState()
    Column {
        Toolbar()
        if (factsState.value.isLoading) {

        }
        if (factsState.value.isError) {

        }
        if (factsState.value.listOfFacts.isNotEmpty()) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(title = { Text(text = "Facts List") })
}