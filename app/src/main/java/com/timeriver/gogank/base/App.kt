package com.timeriver.gogank.base

import android.app.Application
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Gander.setGanderStorage(GanderIMDB.getInstance())

        startKoin {
            androidContext(this@App)
            modules(koinModule)
        }
    }
}