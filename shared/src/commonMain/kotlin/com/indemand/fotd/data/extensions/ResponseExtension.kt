package com.indemand.fotd.data.extensions

import com.indemand.fotd.core.CommonResponse
import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.core.Unknown
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

suspend inline fun <T, X> safeApiCall(
    serializer: KSerializer<T>,
    crossinline apiCall: suspend () -> HttpResponse,
    successTransform: (T?) -> X
): Either<X, IFailure> {
    return try {
        val httpResponse = withContext(Dispatchers.IO) {
            apiCall()
        }

        val responseText = httpResponse.bodyAsText()
        val parsedResponse = Json.decodeFromString<CommonResponse>(responseText)
        //val parsedResponse: CommonResponse<T> = Json.decodeFromString(serializer, responseText)

        if (parsedResponse.status == 200) {
            println("Response: 200")
            val parsedData: T =
                json.decodeFromJsonElement(serializer, parsedResponse.data ?: JsonNull)
            Either.Success(successTransform(parsedData))
        } else {
            println("Response: ${parsedResponse.status}")
            Either.Error(Unknown(parsedResponse.message ?: "Unknown error"))
        }

    } catch (e: Exception) {
        println("Response: ${e.message}")
        Either.Error(Unknown(e.message ?: "Unexpected error"))
    }
}
