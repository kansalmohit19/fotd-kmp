package com.indemand.fotd.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionName")
@Composable
fun MoreScreenView() {
    val modifier = Modifier
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xFF102131)),
    ) {
        Toolbar()
    }
}

@Suppress("FunctionName")
@Composable
private fun Toolbar() {
    Spacer(modifier = Modifier.height(80.dp))
    Text(
        text = "More Section!",
        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFFFFFF)),
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Coming Soon...",
        style = TextStyle(fontSize = 24.sp, color = Color(0xFFFFFFFF)),
    )
}
