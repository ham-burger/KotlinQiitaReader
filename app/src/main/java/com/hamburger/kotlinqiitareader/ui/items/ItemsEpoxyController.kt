package com.hamburger.kotlinqiitareader.ui.items

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.hamburger.kotlinqiitareader.ItemCellBindingModel_
import com.hamburger.kotlinqiitareader.service.ItemDTO

class ItemsEpoxyController(
        val delegate: ItemsDelegate
) : PagedListEpoxyController<ItemDTO>() {
    override fun buildItemModel(currentPosition: Int, item: ItemDTO?): EpoxyModel<*> {
        return ItemCellBindingModel_()
                .item(item)
                .delegate(delegate)
                .id("item$currentPosition")
    }
}
