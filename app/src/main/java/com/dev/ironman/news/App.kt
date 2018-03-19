package com.dev.ironman.news

import android.app.Application
import com.dev.ironman.news.di.components.AppComponent
import com.dev.ironman.news.di.components.DaggerAppComponent


class App : Application() {
    companion object {
        lateinit var daggerComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder().application(this).build()
        daggerComponent.inject(this)
    }
}