package com.hamburger.kotlinqiitareader.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemWebApi {
    interface Request {
        @GET("items.json")
        fun get(@Query("page") page: Int): Observable<ItemsDTO>
    }

    val request: Request
        get() = WebAPIClient.retrofit.create(Request::class.java)
}