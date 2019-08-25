package com.hamburger.kotlinqiitareader.extension

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.service.glide.GlideHolder


@BindingAdapter("imageUrl")
fun ImageView.loadWebImage(
    imageUrl: String?
) {
    if (imageUrl.isNullOrBlank()) {
        GlideHolder.glide
            .load(ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher, context.theme))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
        return
    }

    GlideHolder.glide
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
