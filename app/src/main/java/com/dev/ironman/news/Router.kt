package com.dev.ironman.news

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.ui.AllNewsFragment

class Router(val context: Context) {

    lateinit var fragmentManager: FragmentManager

    fun showAllNewsFragment() {
        doTransaction(AllNewsFragment(), true, "All")
    }

    fun showDetailWebViewFragment(url: String) {
        CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(url))
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

    fun showNewsFragment(map: Map<String, String>?) {
        val fragment = AllNewsFragment()
        val args = Bundle()
        args.putString("category", map?.get("category"))
        args.putString("country", map?.get("country"))
        args.putString("q", map?.get("q"))
        fragment.arguments = args
        doTransaction(fragment, true, "All")
    }
}