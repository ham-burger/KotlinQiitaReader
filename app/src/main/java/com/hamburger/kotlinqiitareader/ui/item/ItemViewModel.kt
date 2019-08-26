package com.hamburger.kotlinqiitareader.ui.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.dto.ItemDetailDTO
import com.hamburger.kotlinqiitareader.service.paging.NetworkState
import com.hamburger.kotlinqiitareader.service.web_api.ItemWebApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ItemViewModel(
    val id: String
) : ViewModel() {
    class Factory(
        private val id: String
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemViewModel(id) as T
        }
    }

    var data = MutableLiveData<ItemDetailDTO?>().apply {
        value = null
    }
    val networkState = MutableLiveData<NetworkState>().apply {
        value = NetworkState.SUCCESS
    }
    private var compositeDisposable = CompositeDisposable()

    init {
        getItem()
    }

    private fun getItem() {
        ItemWebApi()
            .request
            .show(id)
            .subscribeOnIOThread()
            .observeOnMainThread()
            .subscribe({
                data.postValue(it.body())
            }, {
            })
            .addTo(compositeDisposable)
    }
}