package com.hamburger.kotlinqiitareader.service

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class ItemWebApi {
    interface Request {
        @GET("items.json")
        fun get(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<Response<List<ItemDTO>>>
    }

    val request: Request
        get() = WebAPIClient.retrofit.create(Request::class.java)
}