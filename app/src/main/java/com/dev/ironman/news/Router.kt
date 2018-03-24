package com.dev.ironman.news

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.WebViewFragment

class Router(val context: Context) {

    lateinit var fragmentManager: FragmentManager

    fun showAllNewsFragment() {
        doTransaction(AllNewsFragment(), true, "All")
    }

    fun showDetailWebViewFragment(url: String) {
        val details = WebViewFragment()
        val args = Bundle()
        args.putString("URL", url)
        details.arguments = args
        doTransaction(details, true, "Det")
    }

    private fun doTransaction(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val ft = fragmentManager.beginTransaction().apply {
            replace(R.id.frameForFragments, fragment)
            if (addToBackStack) addToBackStack(tag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
        ft.commit()
    }

    fun isNotFragmentsInContainer() = fragmentManager.backStackEntryCount == 0

    fun remove() {
        fragmentManager.popBackStack()
    }

    fun isCurFragNews() = fragmentManager.findFragmentById(R.id.frameForFragments) is AllNewsFragment

}