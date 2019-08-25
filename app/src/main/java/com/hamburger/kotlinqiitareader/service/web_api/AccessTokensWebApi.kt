package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.dto.AccessTokenDTO
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

class AccessTokensWebApi {
    interface Request {
        @POST("access_tokens")
        fun create(
                @Query("client_id") clientId: String,
                @Query("client_secret") clientSecret: String,
                @Query("code") code: String
        ): Observable<Response<AccessTokenDTO>>

        @DELETE("access_tokens/{access_token}")
        fun delete(@Path("access_token") accessToken: String): Observable<Response<Unit>>
    }

    val request: Request
        get() = WebAPIClient.retrofit.create(Request::class.java)
}