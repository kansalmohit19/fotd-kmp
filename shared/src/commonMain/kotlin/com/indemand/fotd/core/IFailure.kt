package com.indemand.fotd.core

interface IFailure {
    val errorMessage: String
}

data class Network(override val errorMessage: String) : IFailure
data class Server(override val errorMessage: String) : IFailure
data class Unknown(override val errorMessage: String) : IFailure
