package com.indemand.fotd.android

import android.app.Application
import com.google.firebase.FirebaseApp
import com.indemand.fotd.android.di.viewModelsModule
import com.indemand.fotd.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        FirebaseApp.initializeApp(this@MyApplication)
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule

        startKoin {
            androidContext(this@MyApplication)
            modules(modules)
        }
    }
}
