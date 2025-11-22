package com.indemand.fotd.core

sealed class Either<out S, out E> where S : Any? {

    data class Success<out S>(val successVal: S) : Either<S, Nothing>()
    data class Error<out E>(val errorVal: E) : Either<Nothing, E>()

    val isSuccess get() = this is Success<S>

    val isError get() = this is Error<E>

    fun evaluate(fnS: (S) -> Any, fnE: (E) -> Any): Any = when (this) {
        is Success -> fnS(successVal)
        is Error -> fnE(errorVal)
    }

    fun errorValue() = if (this is Error) errorVal else null

    fun successValue() = if (this is Success) successVal else null
}
