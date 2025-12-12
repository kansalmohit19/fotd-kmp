package com.indemand.fotd.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.ui.resources.loadAssetIcon
import com.indemand.fotd.viewmodel.main.MainViewModel

@Composable
fun BottomBarView(mainViewModel: MainViewModel) {
    val tabsList = mainViewModel.tabsListFlow.collectAsState()

    val bgColor = MaterialTheme.colorScheme.surface
    val selectedColor = Color.White
    val unselectedColor = Color.Gray
    val indicatorColor = Color.White.copy(alpha = 0.12f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        tabsList.value.forEach { item ->
            val navItem = item.navItem
            BottomBarItem(
                selected = item.isSelected,
                onClick = {
                    mainViewModel.onTabSelected(navItem)
                    navItem.onClick()
                },
                selectedIcon = painterResource(loadAssetIcon(navItem)),
                unselectedIcon = painterResource(loadAssetIcon(navItem)),
                label = navItem.label,
                selectedColor = Color.White,
                unselectedColor = Color.Gray,
                modifier = Modifier.weight(1f)
            )
        }
    }

    LaunchedEffect(tabsList.value) {
        val selectedItem = tabsList.value.firstOrNull { it.isSelected }
        selectedItem?.navItem?.onClick?.invoke()
    }
}

@Composable
private fun BottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: Painter,
    unselectedIcon: Painter,
    label: String,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    modifier: Modifier = Modifier
) {
    val color = if (selected) selectedColor else unselectedColor

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(40))
            .background(
                if (selected) selectedColor.copy(alpha = 0.12f)
                else Color.Transparent
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                painter = if (selected) selectedIcon else unselectedIcon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(26.dp)
            )

            if (selected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label, color = color, style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}