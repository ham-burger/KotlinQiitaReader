package com.hamburger.kotlinqiitareader.service.paging


import androidx.paging.DataSource
import com.hamburger.kotlinqiitareader.service.ItemDTO
import com.hamburger.kotlinqiitareader.service.ItemWebApi

class ItemsDataSourceFactory(api: ItemWebApi) : DataSource.Factory<Int, ItemDTO>() {
    val source = PageKeyedItemsDataSource(api)
    override fun create(): DataSource<Int, ItemDTO> {
        return source
    }
}