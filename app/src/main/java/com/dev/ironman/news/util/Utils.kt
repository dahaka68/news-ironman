package com.dev.ironman.news.util

import android.app.Activity
import android.support.v4.app.Fragment
import com.dev.ironman.news.App
import com.dev.ironman.news.di.components.AppComponent

val Fragment.daggerComponent: AppComponent
    get() {
        return (requireContext().applicationContext as App).daggerComponent
    }

val Activity.daggerComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }