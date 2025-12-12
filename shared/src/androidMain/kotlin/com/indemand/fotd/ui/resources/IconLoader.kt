package com.indemand.fotd.ui.resources

import com.indemand.fotd.R
import com.indemand.fotd.domain.model.BottomNavItem

fun loadAssetIcon(item: BottomNavItem) = when (item) {
    is BottomNavItem.Blog -> R.drawable.ic_blog
    is BottomNavItem.Home -> R.drawable.ic_home
    is BottomNavItem.More -> R.drawable.ic_more
}