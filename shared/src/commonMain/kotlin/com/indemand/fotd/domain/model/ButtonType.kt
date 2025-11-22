package com.indemand.fotd.domain.model

sealed interface ButtonType {
    data class PositiveButton(
        val text: String, val appPackageName: String? = null, val appLink: String? = null
    ) : ButtonType

    data class NegativeButton(val text: String) : ButtonType
}