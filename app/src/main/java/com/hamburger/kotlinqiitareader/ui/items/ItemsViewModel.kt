package com.hamburger.kotlinqiitareader.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hamburger.kotlinqiitareader.BuildConfig
import com.hamburger.kotlinqiitareader.extension.observeOnMainThread
import com.hamburger.kotlinqiitareader.extension.subscribeOnIOThread
import com.hamburger.kotlinqiitareader.service.dto.ItemDTO
import com.hamburger.kotlinqiitareader.service.paging.ItemsDataSourceFactory
import com.hamburger.kotlinqiitareader.service.paging.NetworkState
import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder
import com.hamburger.kotlinqiitareader.service.web_api.AccessTokensWebApi
import com.hamburger.kotlinqiitareader.service.web_api.ItemWebApi
import com.hamburger.kotlinqiitareader.service.web_api.UserWebApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.lang.ref.WeakReference

class ItemsViewModel(
        val code: String?,
        val wDelegate: WeakReference<ItemsDelegate>
) : ViewModel() {
    companion object {
        private const val PAGE_SIZE = 100
    }

    var data: LiveData<PagedList<ItemDTO>>
    val networkState: LiveData<NetworkState>
    //    val showSnackBarEvent = MutableLiveData<String>()
    private var compositeDisposable = CompositeDisposable()

    init {
        val factory = ItemsDataSourceFactory(ItemWebApi())
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()

        data = LivePagedListBuilder(factory, config).build()
        networkState = factory.source.networkState
        getAccessToken()
    }

    private fun getAccessToken() {
        code ?: return
        AccessTokensWebApi()
                .request
                .create(
                        BuildConfig.qiitaClientId,
                        BuildConfig.qiitaClientSecret,
                        code
                )
                .flatMap {
                    RepositoryHolder.accessTokenRepository.setAccessToken(it.body().token)
                }
                .flatMap {
                    UserWebApi().request.get()
                }
                .subscribeOnIOThread()
                .observeOnMainThread()
                .subscribe({
                    val delegate = wDelegate.get() ?: return@subscribe
                    delegate.onSuccessLogin()
                }, {
                })
                .addTo(compositeDisposable)
    }

    class Factory(
            private val code: String?,
            private val wDelegate: WeakReference<ItemsDelegate>
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ItemsViewModel(code, wDelegate) as T
        }
    }
}
