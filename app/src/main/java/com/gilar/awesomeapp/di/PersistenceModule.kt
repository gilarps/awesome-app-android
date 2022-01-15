package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.data.local.AppPreferenceRepository
import com.gilar.awesomeapp.data.local.AppPreferenceUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {

    single {
        AppPreferenceRepository(androidContext())
    }

    single {
        AppPreferenceUseCase(get())
    }

}