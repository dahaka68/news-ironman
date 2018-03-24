package com.dev.ironman.news.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by User on 25.03.2018.
 */
class NetwrorkVerify(val context: Application) {

	//TODO better (it's not complete checkeing if the internet there's
	fun checkInternet(): Boolean {
		val wifiCheck: NetworkInfo?
		val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		wifiCheck = connectionManager.activeNetworkInfo

		wifiCheck?.let {
			if (wifiCheck.type == ConnectivityManager.TYPE_WIFI || wifiCheck.type == ConnectivityManager.TYPE_MOBILE)
				return true
		}

		return false
	}
}