package com.hamburger.kotlinqiitareader.service

import java.io.Serializable

data class ItemDTO(
    val id: String,
    val title: String,
    val body: String
) : Serializable