package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.dto.AuthenticatedUserDTO
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

class UserWebApi {
    interface Request {
        @GET("authenticated_user.json")
        fun get(): Observable<Response<AuthenticatedUserDTO>>
    }

    val request: Request
        get() = WebAPIClient.retrofit.create(Request::class.java)
}