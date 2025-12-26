package com.indemand.fotd.android.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Suppress("FunctionName")
@Composable
fun CustomSnackbar(
    isForError: Boolean = true,
    message: String,
    onDismiss: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        if (isForError) Color.Red else Color.Green,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(16.dp),
        ) {
            Text(text = message, color = Color.White)
        }
    }

    // Auto-dismiss after a delay
    LaunchedEffect(message) {
        delay(3000)
        onDismiss()
    }
}
