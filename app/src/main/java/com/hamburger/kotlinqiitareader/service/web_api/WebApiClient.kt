package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.GsonHolder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object WebAPIClient {
    val retrofit: Retrofit by lazy { createRetrofit() }

    private fun createRetrofit(): Retrofit {

        val clientBuilder = OkHttpClient().newBuilder()

        // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
        clientBuilder.addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("OkHttp").d(it)
        }).also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })

        clientBuilder.addInterceptor {
            it.proceed(
                it.request()
                    .newBuilder()
                    .apply {
                        val keyValue = WebRequestHeaderConfig.authorizationKeyValue
                        if (keyValue.second.isNotBlank()) {
                            addHeader(keyValue.first, keyValue.second)
                        }
                    }
                    .build()
            )
        }


        val timeoutValue = 10L
        clientBuilder.connectTimeout(timeoutValue, TimeUnit.SECONDS)
        clientBuilder.readTimeout(timeoutValue, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(timeoutValue, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl(WebAPIUrl.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonHolder.gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.build())
            .build()

        return retrofit
    }
}