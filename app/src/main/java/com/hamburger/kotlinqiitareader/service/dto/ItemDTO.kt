package com.hamburger.kotlinqiitareader.service.dto

import java.io.Serializable

data class ItemDTO(
    val id: String,
    val title: String,
    val body: String
) : Serializable