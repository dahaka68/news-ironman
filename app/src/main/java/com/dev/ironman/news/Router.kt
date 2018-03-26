package com.dev.ironman.news

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.WebViewFragment
import com.dev.ironman.news.util.DET
import com.dev.ironman.news.util.URL

class Router(val context: Context) {

	lateinit var fragmentManager: FragmentManager

	fun showAllNewsFragment() {
		doTransaction(AllNewsFragment(), true, "All")
	}

	fun showDetailWebViewFragment(url: String) {
		val details = WebViewFragment()
		val args = Bundle()
		context.getSharedPreferences(URL, Context.MODE_PRIVATE).edit().putString(URL, url).apply()
		args.putString(URL, url)
		details.arguments = args
		doTransaction(details, true, DET)
	}

	private fun doTransaction(fragment: Fragment, addToBackStack: Boolean, tag: String) {
		val fragmentTransaction = fragmentManager.beginTransaction().apply {
			replace(R.id.frameForFragments, fragment)
			if (addToBackStack) addToBackStack(tag)
			setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		}
		fragmentTransaction.commit()
	}

	fun isNotFragmentsInContainer() = fragmentManager.backStackEntryCount == 0

	fun remove() = fragmentManager.popBackStack()

	fun isCurFragNews() = fragmentManager.findFragmentById(R.id.frameForFragments) is AllNewsFragment
}