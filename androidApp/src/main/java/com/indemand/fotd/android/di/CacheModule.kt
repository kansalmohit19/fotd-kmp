package com.indemand.fotd.android.di

import com.indemand.fotd.KeyValueStorage
import org.koin.dsl.module

val cacheModule =
    module {
        single<KeyValueStorage> { KeyValueStorage(get()) }
    }
