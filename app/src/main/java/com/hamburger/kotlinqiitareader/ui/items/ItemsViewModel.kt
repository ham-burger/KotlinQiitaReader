package com.hamburger.kotlinqiitareader.ui.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.ItemWebApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ItemsViewModel : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    val data = MutableLiveData<List<String>>()
    fun load() {
        ItemWebApi().request.get(1)
            .subscribeOnIOThread()
            .observeOnMainThread()
            .subscribe {
                data.value = it.map { it.id }
            }
            .addTo(compositeDisposable)
    }
}
