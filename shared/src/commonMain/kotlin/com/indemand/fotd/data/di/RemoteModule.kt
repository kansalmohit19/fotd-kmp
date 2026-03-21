package com.indemand.fotd.data.di

import com.indemand.fotd.data.remote.ConfigApi
import com.indemand.fotd.data.remote.ConfigApiImpl
import com.indemand.fotd.data.remote.UserApi
import com.indemand.fotd.data.remote.UserApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {
    single<ConfigApi> { ConfigApiImpl(get()) }
    single<UserApi> { UserApiImpl(get()) }

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