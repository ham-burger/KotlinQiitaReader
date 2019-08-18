package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.Environment


data class WebAPIUrl(val path: String = "") {
    val string: String get() = baseUrl + path

    companion object {
        val baseUrl: String = Environment.server
    }
}
