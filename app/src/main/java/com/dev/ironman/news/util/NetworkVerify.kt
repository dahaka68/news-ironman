package com.dev.ironman.news.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkVerify(val context: Application) {

    fun isNetWorkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null) {
            return true
        }
        return false
    }
}