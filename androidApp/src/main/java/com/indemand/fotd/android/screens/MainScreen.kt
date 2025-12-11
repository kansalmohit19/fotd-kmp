package com.indemand.fotd.android.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.indemand.fotd.domain.uistate.MainUiState
import com.indemand.fotd.viewmodel.main.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = getViewModel()
) {
    val uiFlow = mainViewModel.mainUIFlow.collectAsState()

    Scaffold(
        bottomBar = {
            BottomBarView(mainViewModel)
        }, containerColor = Color(0xFF102131)
    ) { paddingValues ->

        when (uiFlow.value) {

            is MainUiState.Idle -> {}

            is MainUiState.ShowHomeView -> {
                HomeScreenView()
            }

            is MainUiState.ShowFactListView -> {
                FactsListScreenView()
            }

            is MainUiState.ShowMoreView -> {
                MoreScreenView()
            }

            else -> {}
        }
    }
}