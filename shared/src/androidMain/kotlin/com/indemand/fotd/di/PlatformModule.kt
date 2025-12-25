package com.indemand.fotd.di

import com.indemand.fotd.analytics.AndroidFirebaseAnalyticsProvider
import com.indemand.fotd.analytics.AndroidMParticleAnalyticsProvider
import com.indemand.fotd.analytics.provider.FirebaseAnalyticsProvider
import com.indemand.fotd.analytics.provider.MParticleAnalyticsProvider
import com.indemand.fotd.data.local.LocalDataSource
import com.indemand.fotd.datastore.AndroidDataSourceImpl
import com.indemand.fotd.datastore.dataStore
import com.indemand.fotd.notification.AndroidNotificationTokenProvider
import com.indemand.fotd.notification.provider.NotificationTokenProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidSharedModule = module {
    single<LocalDataSource> { AndroidDataSourceImpl(androidContext().dataStore) }

    //analytics
    singleOf(::AndroidFirebaseAnalyticsProvider) { bind<FirebaseAnalyticsProvider>() }
    singleOf(::AndroidMParticleAnalyticsProvider) { bind<MParticleAnalyticsProvider>() }

    //notification
    singleOf(::AndroidNotificationTokenProvider) { bind<NotificationTokenProvider>() }
}