package com.hamburger.kotlinqiitareader.extension

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("isExist")
fun View.setIsExist(isExist: Boolean) {
    visibility = if (isExist) View.VISIBLE else View.GONE
}
