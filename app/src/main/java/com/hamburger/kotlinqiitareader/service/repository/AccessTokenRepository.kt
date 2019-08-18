package com.hamburger.kotlinqiitareader.service.repository

import android.content.Context
import io.reactivex.Observable
import timber.log.Timber
import java.io.IOException

class AccessTokenRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("AccessToken", Context.MODE_PRIVATE)
    private val keyAccessToken = "keyAccessToken"
    fun setAccessToken(token: String): Observable<Unit> = Observable.create { emitter ->
        if (sharedPreferences.edit().putString(keyAccessToken, token).commit()) {
            emitter.onNext(Unit)
        } else {
            Timber.d("SharedPreferenceの保存に失敗しました。")
            emitter.onError(IOException("SharedPreferenceの保存に失敗しました。"))
        }
    }

    val accessToken get() = sharedPreferences.getString(keyAccessToken, "")
}