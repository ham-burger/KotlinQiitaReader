package com.hamburger.kotlinqiitareader.service.dto

data class AuthenticatedUserDTO(
        val id: String,
        val description: String,
        val name: String?,
        val organization: String?,
        val profileImageUrl: String
)
