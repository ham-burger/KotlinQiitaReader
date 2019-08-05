package com.hamburger.kotlinqiitareader.ui.items

import com.hamburger.kotlinqiitareader.service.ItemDTO

interface ItemsDelegate {
    fun onClickItem(item: ItemDTO)
}