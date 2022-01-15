package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.data.remote.PexelsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { PexelsRepository(get()) }

}
