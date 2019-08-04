package com.hamburger.kotlinqiitareader.ui.items

import com.airbnb.epoxy.TypedEpoxyController
import com.hamburger.kotlinqiitareader.itemCell

class ItemsEpoxyController(
) : TypedEpoxyController<List<Int>>() {
    override fun buildModels(data: List<Int>?) {
        data?.forEach {
            itemCell {
                title(it.toString())
                id(modelCountBuiltSoFar)
            }
        }
    }
}
