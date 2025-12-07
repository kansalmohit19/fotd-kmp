package com.indemand.fotd.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.indemand.fotd.facts.main.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = getViewModel()
) {

    val tabsList = mainViewModel.tabsListFlow.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(paddingValues)
            .background(Color(0xFF102131))
    ) {
        Spacer(modifier = Modifier.weight(1f))

        BottomBarView(mainViewModel)
    }
}