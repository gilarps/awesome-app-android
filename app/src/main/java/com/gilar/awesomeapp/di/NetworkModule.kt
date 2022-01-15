package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.network.AwesomeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val networkModule = module {

  single {
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  single { AwesomeService.create() }

}
