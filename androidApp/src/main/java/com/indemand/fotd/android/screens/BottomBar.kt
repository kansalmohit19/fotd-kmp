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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarView() {
    val bgColor = MaterialTheme.colorScheme.surface
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
    val indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)

    NavigationBar(
        containerColor = Color.Transparent, tonalElevation = 110.dp
    ) {
        // Observe current route
        //val navBackStackEntry by navController.currentBackStackEntryAsState()
        //val currentRoute = navBackStackEntry?.destination?.route

        repeat(3) { item ->
            NavigationBarItem(
                selected = item == 1,
                onClick = {

                },
                icon = { Icon(Icons.Default.Home, contentDescription = "label") },
                label = { Text("Label") },
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