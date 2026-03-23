package com.indemand.fotd.core

interface IFailure {
    val code: Int?
    val message: String?
}

data class HttpFailure(
    override val code: Int? = null,
    override val message: String? = null,
) : IFailure

data class NetworkFailure(
    override val code: Int? = null,
    override val message: String? = null,
) : IFailure

data class ParsingFailure(
    override val code: Int? = null,
    override val message: String? = null,
) : IFailure

data class BackendFailure(
    override val code: Int? = null,
    override val message: String? = null,
) : IFailure

data class UserError(
    override val code: Int? = null,
    override val message: String? = null,
) : IFailure

data class Unknown(
    override val code: Int? = -1,
    override val message: String = "Something went wrong",
) : IFailure
