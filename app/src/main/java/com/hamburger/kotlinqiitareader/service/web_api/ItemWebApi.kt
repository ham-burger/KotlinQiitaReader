package com.hamburger.kotlinqiitareader.service.web_api

import com.hamburger.kotlinqiitareader.service.dto.ItemDTO
import com.hamburger.kotlinqiitareader.service.dto.ItemDetailDTO
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ItemWebApi {
    interface Request {
        @GET("items.json")
        fun index(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<Response<List<ItemDTO>>>

        @GET("items/{id}.json")
        fun show(@Path("id") id: String): Observable<Response<ItemDetailDTO>>
    }

    val request: Request
        get() = WebAPIClient.retrofit.create(Request::class.java)
}