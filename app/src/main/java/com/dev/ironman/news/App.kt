package com.dev.ironman.news

import android.app.Application
import android.arch.persistence.room.Room
import com.dev.ironman.news.data.dao.AppDatabase
import com.dev.ironman.news.di.components.AppComponent
import com.dev.ironman.news.di.components.DaggerAppComponent

class App : Application() {

    internal val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    internal val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "articles").allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}