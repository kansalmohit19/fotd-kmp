package com.indemand.fotd.data.local

import com.indemand.fotd.core.Either
import com.indemand.fotd.core.IFailure
import com.indemand.fotd.data.extensions.safeApiCall
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

enum class CacheType {
    USE_CACHE, REFRESH_CACHE
}

@Serializable
data class CachedData<T>(
    val data: T, val timestamp: Long
)

class ExpirableDataSourceImpl(val cache: LocalDataSource) {

    @OptIn(ExperimentalTime::class)
    suspend inline fun <T> fetch(
        id: String,
        expiryTime: Long,
        cacheType: CacheType,
        serializer: KSerializer<T>,
        crossinline apiCall: suspend () -> HttpResponse
    ): Either<T, IFailure> {

        val cachedData = cache.getString(id)?.let { cached ->
            Json.decodeFromString(
                CachedData.serializer(serializer), cached
            )
        }

        // CACHE_FIRST
        if (cacheType == CacheType.USE_CACHE && cachedData != null) {
            if (!isExpired(cachedData.timestamp, expiryTime)) {
                return Either.Success(cachedData.data)
            }
        }

        // NETWORK CALL
        return when (val networkResult = safeApiCall(serializer, apiCall)) {
            is Either.Success -> {
                val data = networkResult.value

                // Save to cache
                cache.saveString(
                    id, Json.encodeToString(
                        CachedData.serializer(serializer),
                        CachedData(data, Clock.System.now().toEpochMilliseconds())
                    )
                )

                Either.Success(data)
            }

            is Either.Error -> {
                // Fallback to cache if exists
                if (cachedData != null && cacheType != CacheType.REFRESH_CACHE) {
                    Either.Success(cachedData.data)
                } else {
                    networkResult
                }
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    fun isExpired(timestamp: Long, expiryTime: Long): Boolean {
        return Clock.System.now().toEpochMilliseconds() - timestamp > expiryTime
    }
}