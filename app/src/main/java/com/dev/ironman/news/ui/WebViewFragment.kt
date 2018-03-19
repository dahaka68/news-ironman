package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dev.ironman.news.R
import com.dev.ironman.news.mvp.presenters.WebFragmentPresenter
import com.dev.ironman.news.mvp.views.WebFragmentView
import javax.inject.Inject

class WebViewFragment : Fragment(), WebFragmentView {

    @Inject
    lateinit var webFragPresenter: WebFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        webFragPresenter.attachView(this)
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webFragPresenter.detachView()
    }

    override fun showContent(url: String) {
        //TODO: show content of link
    }

}
