package com.indemand.fotd.android.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.domain.model.BottomSheetDetails
import com.indemand.fotd.domain.model.ButtonType

@Suppress("FunctionName")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentBottomSheetDemo(
    details: BottomSheetDetails,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
) {
    var showSheet by remember { mutableStateOf(true) }
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { newValue ->
                // Allow hiding only if cancellable
                if (details.isCancellable == false && newValue == SheetValue.Hidden) {
                    false
                } else {
                    true
                }
            },
        )
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { if (details.isCancellable == true) showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White,
        ) {
            // Content inside bottom sheet
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = details.title.orEmpty(),
                    style =
                        TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black,
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                    textAlign = TextAlign.Start,
                )

                Text(
                    text = details.message.orEmpty(),
                    style =
                        TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF6B6B6B),
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                    textAlign = TextAlign.Start,
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                ) {
                    val buttons =
                        mutableListOf<ButtonType>().apply {
                            details.negativeButton?.let { button ->
                                add(
                                    ButtonType.NegativeButton(
                                        text = button.text,
                                    ),
                                )
                            }

                            details.positiveButton?.let { button ->
                                add(
                                    ButtonType.PositiveButton(
                                        text = button.text,
                                    ),
                                )
                            }
                        }

                    buttons.forEachIndexed { index, type ->
                        AppButton(
                            type = type,
                            onClick = {
                                when (type) {
                                    is ButtonType.PositiveButton -> onPositiveClick()
                                    is ButtonType.NegativeButton -> onNegativeClick()
                                }
                                showSheet = false
                            },
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .padding(end = if (index < buttons.lastIndex) 8.dp else 0.dp),
                        )
                    }
                }
            }
        }
    }
}
