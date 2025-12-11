package com.indemand.fotd.android.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.indemand.fotd.viewmodel.main.MainViewModel

@Composable
fun BottomBarView(mainViewModel: MainViewModel) {
    val tabsList = mainViewModel.tabsListFlow.collectAsState()

    val bgColor = MaterialTheme.colorScheme.surface
    val selectedColor = Color.White
    val unselectedColor = Color.Gray
    val indicatorColor = Color.White.copy(alpha = 0.12f)

    Row {
        tabsList.value.forEach { item ->
            NavigationBarItem(
                selected = item.isSelected ?: false,
                onClick = {
                    mainViewModel.onTabSelected(item)
                    item.onClick()
                },
                icon = { Icon(Icons.Default.Home, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    selectedTextColor = selectedColor,
                    unselectedTextColor = unselectedColor,
                    indicatorColor = indicatorColor
                ),
            )
        }
    }

    LaunchedEffect(tabsList.value) {
        val selectedItem = tabsList.value.firstOrNull { it.isSelected == true }
        selectedItem?.onClick?.invoke()
    }
}