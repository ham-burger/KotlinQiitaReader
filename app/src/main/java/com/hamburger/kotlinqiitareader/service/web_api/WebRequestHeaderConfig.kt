package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder

object WebRequestHeaderConfig {
    val authorizationValue get() = "Bearer ${RepositoryHolder.accessTokenRepository.accessToken}"
    val authorizationKeyValue get() = "Authorization" to authorizationValue
}