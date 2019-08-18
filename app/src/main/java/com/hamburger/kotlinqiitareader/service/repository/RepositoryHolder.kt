package com.hamburger.kotlinqiitareader.service.repository

import android.content.Context

object RepositoryHolder {
    lateinit var accessTokenRepository: AccessTokenRepository
    fun init(context: Context) {
        accessTokenRepository = AccessTokenRepository(context)
    }
}