package com.indemand.fotd.android.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.indemand.fotd.BottomNavItem
import com.indemand.fotd.facts.main.MainViewModel

@Composable
fun BottomBarView(mainViewModel: MainViewModel) {
    val tabsList = mainViewModel.tabsListFlow.collectAsState()

    val bgColor = MaterialTheme.colorScheme.surface
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
    val indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)

    NavigationBar(
        containerColor = Color.Transparent, tonalElevation = 110.dp
    ) {
        tabsList.value.forEach { item ->
            NavigationBarItem(
                selected = item is BottomNavItem.Home,
                onClick = {

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
}