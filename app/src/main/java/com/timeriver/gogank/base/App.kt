package com.timeriver.gogank.base

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules()
        }
    }
}