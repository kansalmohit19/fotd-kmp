package com.indemand.fotd

data class BottomNavItem(
    val label: String,
    val iconURL: String? = "",
    var isSelected: Boolean? = false,
    val onClick: () -> Unit,
)
