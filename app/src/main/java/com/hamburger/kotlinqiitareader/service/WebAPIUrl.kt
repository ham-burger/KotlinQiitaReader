package com.hamburger.kotlinqiitareader.service


data class WebAPIUrl(val path: String = "") {
    val string: String get() = baseUrl + path

    companion object {
        val baseUrl: String = Environment.server
    }
}
