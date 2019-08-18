package com.hamburger.kotlinqiitareader.service.dto

data class AccessTokenDTO(
    val clientId: String,
    val scopes: List<String>,
    val token: String
)
