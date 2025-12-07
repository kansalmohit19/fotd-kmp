package com.indemand.fotd

sealed class BottomNavItem(
    val route: String,
    val label: String,
) {
    object Home : BottomNavItem("home", "Home")
    object Search : BottomNavItem("search", "Search")
    object Profile : BottomNavItem("profile", "Profile")

    companion object {
        val items = listOf(Home, Search, Profile)
    }
}
