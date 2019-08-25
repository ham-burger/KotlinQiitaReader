package com.hamburger.kotlinqiitareader

import android.app.Application
import com.hamburger.kotlinqiitareader.service.glide.GlideHolder
import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlideHolder.initialize(applicationContext)
        Timber.plant(Timber.DebugTree())
        RepositoryHolder.init(applicationContext)
    }
}