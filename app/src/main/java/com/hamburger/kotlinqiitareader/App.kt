package com.hamburger.kotlinqiitareader

import android.app.Application
import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        RepositoryHolder.init(applicationContext)
    }
}