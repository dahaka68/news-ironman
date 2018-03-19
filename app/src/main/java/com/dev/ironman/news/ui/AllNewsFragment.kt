package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.ironman.news.App.Companion.daggerComponent
import com.dev.ironman.news.R
import com.dev.ironman.news.adapters.AllNewsAdapter
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.restModels.ArticlesItem
import kotlinx.android.synthetic.main.fragment_all_news.view.*
import javax.inject.Inject

class AllNewsFragment : Fragment(), AllNewsFragmentView {

    @Inject
    lateinit var allNewsFragmentPresenter: AllNewsFragmentPresenter

    lateinit var listOfNews: RecyclerView

    lateinit var adapter: AllNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_all_news, container, false)
        allNewsFragmentPresenter.attachView(this)
        allNewsFragmentPresenter.showNews()
        listOfNews = view.rcvnewstitleslist
        listOfNews.layoutManager = LinearLayoutManager(context)
        adapter = AllNewsAdapter()
        listOfNews.adapter = adapter
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        allNewsFragmentPresenter.detachView()
    }

    override fun showAllNews(list: List<ArticlesItem?>?) {
        if (list != null) {
            adapter.listOfNews = list
        }
        adapter.notifyDataSetChanged()
    }
}
