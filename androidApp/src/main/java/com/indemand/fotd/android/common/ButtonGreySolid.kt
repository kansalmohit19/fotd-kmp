package com.indemand.fotd.android.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonGreySolid(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE1E1E1),
    contentColor: Color = Color(0xFF212121),
    enabled: Boolean = true,
    cornerRadius: Dp = 15.dp,
    textStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor,
            ),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
        )
    }
}
