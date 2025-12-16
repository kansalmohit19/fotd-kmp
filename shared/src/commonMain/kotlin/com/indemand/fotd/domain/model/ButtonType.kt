package com.indemand.fotd.domain.model

sealed interface ButtonType {
    data class PositiveButton(val text: String) : ButtonType
    data class NegativeButton(val text: String) : ButtonType
}