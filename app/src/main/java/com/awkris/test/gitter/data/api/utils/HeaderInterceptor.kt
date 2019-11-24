package com.awkris.test.gitter.data.api.utils

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept", "application/vnd.github.v3+json")
            .addHeader("Return-type", "text/json")
            .build()

        return chain.proceed(request)
    }
}
