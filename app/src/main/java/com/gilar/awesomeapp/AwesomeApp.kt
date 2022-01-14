@file:Suppress("unused")

package com.gilar.awesomeapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AwesomeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AwesomeApp)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
