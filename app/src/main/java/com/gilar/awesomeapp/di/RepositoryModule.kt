package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.network.AwesomeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { AwesomeRepository(get()) }

}
