package com.hamburger.kotlinqiitareader.service.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.ItemDTO
import com.hamburger.kotlinqiitareader.service.ItemWebApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import java.io.IOException

class PageKeyedItemsDataSource(private val api: ItemWebApi) : PageKeyedDataSource<Int, ItemDTO>() {
    val networkState = MutableLiveData<NetworkState>().also {
        it.value = NetworkState.SUCCESS
    }
    private var compositeDisposable = CompositeDisposable()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ItemDTO>) {
        // 今回は使ってない
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ItemDTO>) {
        callAPI(params.key, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, next)
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ItemDTO>) {
        callAPI(1, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, null, next)
        }
    }

    private fun callAPI(page: Int, perPage: Int, callback: (repos: List<ItemDTO>, next: Int?) -> Unit) {
        networkState.postValue(NetworkState.RUNNING)
        try {
            api.request.get(page, perPage)
                .subscribeOnIOThread()
                .observeOnMainThread()
                .subscribe({
                    if (it.isSuccessful) {
                        val lastPage = it.headers()["total-count"]?.toIntOrNull()?.plus(1)
                        val next = if (page == lastPage) null else page + 1
                        callback(it.body(), next)
                    } else {
                        networkState.postValue(NetworkState.FAILED)
                    }
                    networkState.postValue(NetworkState.SUCCESS)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
                .addTo(compositeDisposable)
        } catch (e: IOException) {
            Timber.w(e)
            networkState.postValue(NetworkState.FAILED)
        }
    }
}