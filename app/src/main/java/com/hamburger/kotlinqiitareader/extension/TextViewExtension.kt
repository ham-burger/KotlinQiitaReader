package com.hamburger.kotlinqiitareader.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import io.noties.markwon.Markwon

@BindingAdapter("markdown")
fun TextView.setMarkdown(body: String?) {
    body ?: return
    Markwon.create(context).setMarkdown(this, body)
}