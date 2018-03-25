package com.dev.ironman.news.util

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast
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

fun Activity.toast(message: String){
    Toast.makeText(this, message , Toast.LENGTH_LONG).show()
}

fun Fragment.toast(message: String){
    Toast.makeText(requireContext().applicationContext as App, message , Toast.LENGTH_LONG).show()
}