package com.hamburger.kotlinqiitareader.ui.items

import com.airbnb.epoxy.TypedEpoxyController
import com.hamburger.kotlinqiitareader.itemCell

class ItemsEpoxyController(
) : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>?) {
        data?.forEach {
            itemCell {
                title(it)
                id(modelCountBuiltSoFar)
            }
        }
    }
}
