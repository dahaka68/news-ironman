package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.dev.ironman.news.App
import com.dev.ironman.news.R
import com.dev.ironman.news.adapters.AllNewsAdapter
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.restModels.ArticlesItem
import kotlinx.android.synthetic.main.fragment_all_news.view.*
import javax.inject.Inject

class AllNewsFragment : Fragment(), AllNewsFragmentView, IDetail {

    @Inject
    lateinit var allNewsFragmentPresenter: AllNewsFragmentPresenter

    private lateinit var listOfNews: RecyclerView
    private lateinit var adapter: AllNewsAdapter
    private lateinit var prB: FrameLayout
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App().daggerComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_all_news, container, false)
        prB = view.prBar

        allNewsFragmentPresenter.attachView(this)
        allNewsFragmentPresenter.showNews()
        linearLayoutManager = LinearLayoutManager(context)
        listOfNews = view.rcvnewstitleslist
        listOfNews.layoutManager = linearLayoutManager
        adapter = AllNewsAdapter(this)
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

    override fun showProgress() {
        prB.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prB.visibility = View.GONE
    }

    override fun goToDetail(url: String) {
        allNewsFragmentPresenter.router.fragmentManager = (activity as MainActivity).supportFragmentManager
        allNewsFragmentPresenter.goToNewDetails(url)
    }
}
