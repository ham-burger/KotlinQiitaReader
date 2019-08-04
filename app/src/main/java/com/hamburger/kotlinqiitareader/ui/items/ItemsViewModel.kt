package com.hamburger.kotlinqiitareader.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hamburger.kotlinqiitareader.service.ItemDTO
import com.hamburger.kotlinqiitareader.service.ItemWebApi
import com.hamburger.kotlinqiitareader.service.paging.ItemsDataSourceFactory
import com.hamburger.kotlinqiitareader.service.paging.NetworkState

class ItemsViewModel : ViewModel() {
    companion object {
        private const val PAGE_SIZE = 50
    }

    var data: LiveData<PagedList<ItemDTO>>
    val networkState: LiveData<NetworkState>

    init {
        val factory = ItemsDataSourceFactory(ItemWebApi())
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()

        data = LivePagedListBuilder(factory, config).build()
        networkState = factory.source.networkState
    }
}
