package com.hamburger.kotlinqiitareader.service

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object OkHttpFactory {
    // @see http://stackoverflow.com/questions/25509296/trusting-all-certificates-with-okhttp
    // Create a trust manager that does not validate certificate chains
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager

    fun createUnsafeOkHttpClient(): OkHttpClient {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).hostnameVerifier { hostname, session -> true }
            .build()
    }
}