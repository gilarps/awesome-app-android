package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.data.remote.PexelsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

  single {
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  single { PexelsService.create(androidContext()) }

}
