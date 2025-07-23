package com.indemand.fotd.android.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SecondaryInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isInvisibleText: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color(0xFF979EA6))
        },
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color(0xFF243545),
            unfocusedIndicatorColor = Color(0xFF243545),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF3B4C59),
            unfocusedContainerColor = Color(0xFF3B4C59),
            cursorColor = Color.White
        ),
        modifier = modifier,
        visualTransformation = if (isInvisibleText) PasswordVisualTransformation() else VisualTransformation.None
    )
}