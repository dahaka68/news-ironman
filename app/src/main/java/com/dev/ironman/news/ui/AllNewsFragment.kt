package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.ironman.news.App.Companion.daggerComponent
import com.dev.ironman.news.R
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import kotlinx.android.synthetic.main.fragment_all_news.view.*
import javax.inject.Inject

class AllNewsFragment : Fragment(), AllNewsFragmentView {

    @Inject
    lateinit var allNewsFragmentPresenter: AllNewsFragmentPresenter

    lateinit var news: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        allNewsFragmentPresenter.attachView(this)
        allNewsFragmentPresenter.showNews()
        val view: View = inflater.inflate(R.layout.fragment_all_news, container, false)
        news = view.textViewNews
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        allNewsFragmentPresenter.detachView()
    }

    override fun showAllNews(allnews: String) {
        news.setText(allnews)
    }


}
