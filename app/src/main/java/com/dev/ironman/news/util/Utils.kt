package com.dev.ironman.news

import android.app.Activity
import android.support.v4.app.Fragment
import com.dev.ironman.news.di.components.AppComponent
import java.text.SimpleDateFormat
import java.util.*

val Fragment.daggerComponent: AppComponent
    get() {
        return (requireContext().applicationContext as App).daggerComponent
    }

val Activity.daggerComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }

