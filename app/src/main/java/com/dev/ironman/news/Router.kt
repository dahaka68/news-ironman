package com.dev.ironman.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.WebViewFragment
import android.content.Context
import android.content.res.Configuration

class Router(val context: Context) {

    lateinit var fragmentManager: FragmentManager

    fun showAllNewsFragment() {
        doTransaction(AllNewsFragment(), false)
    }

    fun showDetailWebViewFragment(url: String) {
        val details = WebViewFragment()
        val args = Bundle()
        args.putString("URL", url)
        details.arguments = args
        doTransaction(details, true)
    }

    private fun doTransaction(fragment: Fragment, addToBackStack: Boolean) {
        val ft = fragmentManager.beginTransaction().apply {
            replace(R.id.frameForFragments, fragment)
            if (addToBackStack) addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
        ft.commit()
    }

    fun isTabletOrLandScape(context: Context): Boolean {
        val xlarge = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        val land = context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        return xlarge || large || land
    }
}