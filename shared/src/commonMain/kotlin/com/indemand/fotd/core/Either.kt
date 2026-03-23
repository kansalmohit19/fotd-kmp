package com.indemand.fotd.core

sealed class Either<out S, out F> {
    data class Success<out S>(
        val value: S,
    ) : Either<S, Nothing>()

    data class Error<out F>(
        val error: F,
    ) : Either<Nothing, F>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    inline fun <R> fold(
        onSuccess: (S) -> R,
        onError: (F) -> R,
    ): R =
        when (this) {
            is Success -> onSuccess(value)
            is Error -> onError(error)
        }

    /*inline fun <R> map(transform: (S) -> R): Either<R, F> = when (this) {
        is Success -> Success(transform(value))
        is Error -> this
    }*/

    inline fun <R> flatMap(transform: (S) -> Either<R, @UnsafeVariance F>): Either<R, F> =
        when (this) {
            is Success -> transform(value)
            is Error -> this
        }

    fun successValue() = if (this is Success) value else null
}
