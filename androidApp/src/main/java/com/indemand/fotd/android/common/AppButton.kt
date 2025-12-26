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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.domain.model.ButtonType
import com.indemand.fotd.domain.model.Quadruple

@Suppress("FunctionName")
@Composable
fun AppButton(
    type: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (text, bgColor, textColor, padding) =
        when (type) {
            is ButtonType.PositiveButton ->
                Quadruple(
                    type.text,
                    Color(0xFF212121),
                    Color(0xFFDFDFDF),
                    Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                )

            is ButtonType.NegativeButton ->
                Quadruple(
                    type.text,
                    Color(0xFFE1E1E1),
                    Color(0xFF212121),
                    Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                )
        }

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        shape = RoundedCornerShape(14.dp),
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 16.sp, color = textColor, fontWeight = FontWeight.Medium),
            modifier = padding,
        )
    }
}
