package com.indemand.fotd.di

import com.indemand.fotd.NotificationTokenProvider
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.datastore.AndroidDataSourceImpl
import com.indemand.fotd.datastore.dataStore
import com.indemand.fotd.notification.AndroidNotificationTokenProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSharedModule = module {
    single<NotificationTokenProvider> { AndroidNotificationTokenProvider() }
    single<LocalDataSource> { AndroidDataSourceImpl(androidContext().dataStore) }
}