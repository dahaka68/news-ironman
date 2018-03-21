package com.dev.ironman.news

import android.app.Application
import com.dev.ironman.news.di.components.AppComponent
import com.dev.ironman.news.di.components.DaggerAppComponent

class App : Application() {

    internal val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}