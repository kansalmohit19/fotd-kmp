package com.indemand.fotd.domain.model

sealed class BottomNavItem(
    val label: String, open var onClick: () -> Unit
) {
    data class Blog(override var onClick: () -> Unit) : BottomNavItem("Blog", onClick = onClick)
    data class Home(override var onClick: () -> Unit) : BottomNavItem("Home", onClick = onClick)
    data class More(override var onClick: () -> Unit) : BottomNavItem("More", onClick = onClick)
}

data class BottomNavUiItem(
    val navItem: BottomNavItem, val isSelected: Boolean
)