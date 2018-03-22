package com.dev.ironman.news

import android.app.Activity
import android.support.v4.app.Fragment
import com.dev.ironman.news.di.components.AppComponent

val Fragment.appComponent: AppComponent
    get() {
        return (requireContext().applicationContext as App).daggerComponent
    }

val Activity.appComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }