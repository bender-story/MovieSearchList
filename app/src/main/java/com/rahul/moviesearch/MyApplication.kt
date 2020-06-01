package com.rahul.moviesearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.rahul.moviesearch.di.appModule

/**
 * Application context
 */
class MyApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(appModule)
        }
    }
}