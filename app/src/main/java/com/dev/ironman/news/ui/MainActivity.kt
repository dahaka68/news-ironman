package com.dev.ironman.news.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dev.ironman.news.R
import com.dev.ironman.news.util.daggerComponent
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.mvp.views.MainActivityView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        daggerComponent.inject(this)
        mainPresenter.router.fragmentManager = supportFragmentManager
    }

    override fun isTablet(): Boolean {
        val xlarge = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!isTablet() && mainPresenter.isNotFragmentsInContainer()) {
            finish()
        }

        if (isTablet()) {
            finish()
        }
    }

}
