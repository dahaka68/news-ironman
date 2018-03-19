package com.dev.ironman.news.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.dev.ironman.news.App
import com.dev.ironman.news.R
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.mvp.views.MainActivityView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App().daggerComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.detachView()
    }

    override fun fragmentManager(): FragmentManager {
        return supportFragmentManager
    }

}
