package com.hamburger.kotlinqiitareader.service.paging


import androidx.paging.DataSource
import com.hamburger.kotlinqiitareader.service.dto.ItemDTO
import com.hamburger.kotlinqiitareader.service.web_api.ItemWebApi

class ItemsDataSourceFactory(api: ItemWebApi) : DataSource.Factory<Int, ItemDTO>() {
    val source = PageKeyedItemsDataSource(api)
    override fun create(): DataSource<Int, ItemDTO> {
        return source
    }
}