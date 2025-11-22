package com.indemand.fotd.domain.model

data class BottomSheetDetails(
    val title: String? = "",
    val message: String? = "",
    val isCancellable: Boolean? = true,
    val negativeButton: ButtonType.NegativeButton? = null,
    val positiveButton: ButtonType.PositiveButton? = null
)