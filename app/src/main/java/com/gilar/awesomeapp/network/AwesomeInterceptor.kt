package com.gilar.awesomeapp.network

import com.gilar.awesomeapp.util.PEXELS_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Request interceptor to add access key (access_key) as query parameter
 * */
class AwesomeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .addHeader("Authorization", PEXELS_API_KEY)
            .build()
        return chain.proceed(request)
    }
}
