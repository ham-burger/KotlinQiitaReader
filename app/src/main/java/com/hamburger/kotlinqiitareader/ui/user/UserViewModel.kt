package com.hamburger.kotlinqiitareader.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.dto.AuthenticatedUserDTO
import com.hamburger.kotlinqiitareader.service.web_api.UserWebApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class UserViewModel(
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

}
