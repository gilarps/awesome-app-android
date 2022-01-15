package com.gilar.awesomeapp.data.remote

import com.gilar.awesomeapp.data.model.PhotosCuratedResponse
import com.gilar.awesomeapp.util.PEXELS_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsService {

    @GET("curated")
    suspend fun fetchPhotos(
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 20,
    ): PhotosCuratedResponse

    companion object {

        fun create(): PexelsService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(PexelsInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(PEXELS_BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
                .create(PexelsService::class.java)
        }
    }
}
