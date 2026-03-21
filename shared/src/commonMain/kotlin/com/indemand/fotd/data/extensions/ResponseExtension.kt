package com.indemand.fotd.data.extensions

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.HttpFailure
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.NetworkFailure
import com.indemand.fotd.core.ParsingFailure
import com.indemand.fotd.core.Unknown
import com.indemand.fotd.data.model.ConfigurationDetailsDTO
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

suspend inline fun <T> safeApiCall(
    serializer: KSerializer<T>,
    crossinline apiCall: suspend () -> HttpResponse,
): Either<T, IFailure> {
    return try {
        val response = withContext(Dispatchers.IO) {
            apiCall()
        }

        if (!response.status.isSuccess()) {
            return Either.Error(
                HttpFailure(
                    code = response.status.value,
                    message = response.status.description,
                ),
            )
        }

        val body = response.bodyAsText()
        val parsed = Json.decodeFromString(serializer, body)
        Either.Success(parsed)
    } catch (_: IOException) {
        Either.Error(NetworkFailure())
    } catch (_: SerializationException) {
        Either.Error(ParsingFailure())
    } catch (e: Exception) {
        Either.Error(Unknown(message = e.message ?: "Unexpected error"))
    }
}

suspend inline fun <X> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse,
    successTransform: (ConfigurationDetailsDTO?) -> X
): Either<X, IFailure> {
    return try {
        val httpResponse = withContext(Dispatchers.IO) {
            apiCall()
        }

        //val responseText = httpResponse.bodyAsText()
        val parsedData = json.decodeFromJsonElement(
            ConfigurationDetailsDTO.serializer(),
            httpResponse.body() ?: JsonNull
        )
        Either.Success(successTransform(parsedData))

    } catch (e: Exception) {
        println("Response: ${e.message}")
        Either.Error(Unknown(e.message ?: "Unexpected error"))
    }
}
