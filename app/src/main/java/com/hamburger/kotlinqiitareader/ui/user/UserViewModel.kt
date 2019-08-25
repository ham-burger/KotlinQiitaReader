package com.hamburger.kotlinqiitareader.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.dto.AuthenticatedUserDTO
import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder
import com.hamburger.kotlinqiitareader.service.web_api.AccessTokensWebApi
import com.hamburger.kotlinqiitareader.service.web_api.UserWebApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import java.lang.ref.WeakReference

class UserViewModel(
        val wDelegate: WeakReference<UserDelegate>
) : ViewModel() {
    private var compositeDisposable = CompositeDisposable()

    val authenticatedUserDTO = MutableLiveData<AuthenticatedUserDTO>().apply {
        value = null
    }

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        UserWebApi()
                .request
                .get()
                .subscribeOnIOThread()
                .observeOnMainThread()
                .subscribe({
                    authenticatedUserDTO.postValue(it.body())
                }, {
                })
                .addTo(compositeDisposable)
    }

    fun logout() {
        Timber.d("ログアウト")
//        api実行 -> 成功したらアクセストークン削除 -> 成功したら画面閉じる

        val token = RepositoryHolder.accessTokenRepository.accessToken ?: return
        AccessTokensWebApi()
                .request
                .delete(token)
                .flatMap {
                    Observable.just(RepositoryHolder.accessTokenRepository.deleteAccessToken())
                }
                .subscribeOnIOThread()
                .observeOnMainThread()
                .subscribe({
                    val delegate = wDelegate.get() ?: return@subscribe
                    delegate.onSuccessLogout()
                }, {
                })
                .addTo(compositeDisposable)
    }

    class Factory(
            private val wDelegate: WeakReference<UserDelegate>
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(wDelegate) as T
        }
    }
}
