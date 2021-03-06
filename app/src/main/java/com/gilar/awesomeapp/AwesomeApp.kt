@file:Suppress("unused")

package com.gilar.awesomeapp

import android.app.Application
import com.gilar.awesomeapp.di.networkModule
import com.gilar.awesomeapp.di.persistenceModule
import com.gilar.awesomeapp.di.repositoryModule
import com.gilar.awesomeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AwesomeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AwesomeApp)
            modules(viewModelModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(persistenceModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
