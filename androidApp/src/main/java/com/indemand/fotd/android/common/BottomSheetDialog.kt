package com.indemand.fotd.android.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.domain.model.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentBottomSheetDemo() {
    var showSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            // Content inside bottom sheet
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "time to update!",
                    style = TextStyle(
                        fontSize = 24.sp, color = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "we have added a lot of new features for you.\nplease, update it to the latest version.",
                    style = TextStyle(
                        fontSize = 18.sp, color = Color(0xFF6B6B6B),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    textAlign = TextAlign.Start
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                ) {
                    val showNegative = true
                    val showPositive = true

                    val buttons = mutableListOf<ButtonType>().apply {
                        if (showNegative) add(
                            ButtonType.NegativeButton(text = "negative",
                                onClick = { showSheet = false })
                        )
                        if (showPositive) add(
                            ButtonType.PositiveButton(text = "positive",
                                onClick = { showSheet = false })
                        )
                    }

                    buttons.forEachIndexed { index, type ->
                        AppButton(
                            type = type,
                            onClick = { showSheet = false },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = if (index < buttons.lastIndex) 8.dp else 0.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransparentBottomSheetDemo() {
    TransparentBottomSheetDemo()
}