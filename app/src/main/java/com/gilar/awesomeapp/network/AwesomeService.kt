package com.gilar.awesomeapp.network

import com.gilar.awesomeapp.data.PhotosCuratedResponse
import com.gilar.awesomeapp.util.PEXELS_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AwesomeService {

    @GET("curated")
    fun fetchPhotos(
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 20,
    ): PhotosCuratedResponse

    companion object {

        fun create(): AwesomeService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(AwesomeInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(PEXELS_BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
                .create(AwesomeService::class.java)
        }
    }
}
