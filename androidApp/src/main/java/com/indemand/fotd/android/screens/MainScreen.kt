package com.indemand.fotd.android.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.indemand.fotd.analytics.receiver.AnalyticsReceiver
import com.indemand.fotd.domain.uistate.MainUiState
import com.indemand.fotd.viewmodel.main.MainViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Suppress("FunctionName")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    analyticsReceiver: AnalyticsReceiver = koinInject()
) {
    val uiFlow = mainViewModel.mainUIFlow.collectAsState()

    Scaffold(
        bottomBar = {
            BottomBarView(mainViewModel)
        },
        containerColor = Color(0xFF102131),
    ) { paddingValues ->

        when (uiFlow.value) {
            is MainUiState.Idle -> {}

            is MainUiState.ShowHomeView -> {
                analyticsReceiver.onTabClick("HOME")
                HomeScreenView(paddingValues = paddingValues)
            }

            is MainUiState.ShowBlogView -> {
                analyticsReceiver.onTabClick("BLOG")
                BlogScreenView(paddingValues = paddingValues)
            }

            is MainUiState.ShowMoreView -> {
                analyticsReceiver.onTabClick("MORE")
                MoreScreenView()
            }

            else -> {}
        }
    }
}
