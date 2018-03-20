package com.dev.ironman.news

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.WebViewFragment

class Router {

    lateinit var fragmentManager: FragmentManager

    fun showAllNewsFragment() {
        doTransaction(AllNewsFragment(), false)
    }

    fun showDetailWebViewFragment(url: String) {
        val fragment = WebViewFragment()
        fragment.setContentUrl(url)
        doTransaction(fragment, true)
    }

    private fun doTransaction(fragment: Fragment, addToBackStack: Boolean) {
        val ft = fragmentManager.beginTransaction().apply {
            replace(R.id.frameForFragments, fragment)
            if (addToBackStack) addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
        ft.commit()
    }
}