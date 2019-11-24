package com.awkris.test.gitter.data.api.utils

import com.google.gson.Gson
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiGenerator(gson: Gson) {

    private val retrofit: Retrofit

    private val okHttpClient: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

            return okHttpClientBuilder.build()
        }

    init {
        retrofit = initRetrofit(gson, okHttpClient)
    }

    fun <T> createApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    private fun initRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    companion object {
        private const val CONNECTION_TIMEOUT = 60
    }
}
