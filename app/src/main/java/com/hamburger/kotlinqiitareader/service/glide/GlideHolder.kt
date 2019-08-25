package com.hamburger.kotlinqiitareader.service.glide

import android.content.Context

object GlideHolder {
    lateinit var glide: GlideRequests
        private set

    fun initialize(context: Context) {
        glide = GlideApp.with(context)
    }
}