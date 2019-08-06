package com.hamburger.kotlinqiitareader.ui.util

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object ChromeCustomTabsLauncher {
    fun launch(context: Context, urlString: String, intentFlags: Int? = null) {
        val tabsIntent = CustomTabsIntent.Builder().build()

        intentFlags?.let {
            tabsIntent.intent.addFlags(it)
        }

        val uri = Uri.parse(urlString)
        tabsIntent.launchUrl(context, uri)
    }
}