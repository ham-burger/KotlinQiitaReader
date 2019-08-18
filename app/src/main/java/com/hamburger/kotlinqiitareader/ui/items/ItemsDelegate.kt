package com.hamburger.kotlinqiitareader.ui.items

import com.hamburger.kotlinqiitareader.service.dto.ItemDTO

interface ItemsDelegate {
    fun onClickItem(item: ItemDTO)
//    fun onClickLogin()
}