package com.indemand.fotd

sealed class BottomNavItem(
    val route: String,
    val label: String,
) {
    object Home : BottomNavItem("home", "Home")
    object List : BottomNavItem("list", "List")
    object Profile : BottomNavItem("profile", "Profile")

    companion object {
        val items = listOf(List, Home, Profile)
    }
}
