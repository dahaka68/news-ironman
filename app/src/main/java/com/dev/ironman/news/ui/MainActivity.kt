package com.dev.ironman.news.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.dev.ironman.news.App.Companion.daggerComponent
import com.dev.ironman.news.R
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.mvp.views.MainActivityView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RouterTarget, MainActivityView {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daggerComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.detachView()
    }

    override fun doTransaction(fragment: Fragment, addToBackStack: Boolean) {
        val ft = supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameForFragments, fragment)
            if (addToBackStack) addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
        ft.commit()
    }
}
