package com.indemand.fotd.data.di

import com.indemand.fotd.data.remote.DataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<DataSourceImpl> { DataSourceImpl(get()) }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true

                }, contentType = ContentType.Any)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }
        }
    }
}