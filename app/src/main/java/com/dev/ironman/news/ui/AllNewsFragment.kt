package com.dev.ironman.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.ironman.news.R
import com.dev.ironman.news.adapters.AllNewsAdapter
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.util.ERROR_FROM_NET
import com.dev.ironman.news.util.NO_INTERNET
import com.dev.ironman.news.util.daggerComponent
import com.dev.ironman.news.util.toast
import kotlinx.android.synthetic.main.fragment_all_news.*
import javax.inject.Inject

class AllNewsFragment : Fragment(), AllNewsFragmentView, IDetail {

    @Inject
    lateinit var allNewsFragmentPresenter: AllNewsFragmentPresenter

    private val adapter: AllNewsAdapter by lazy {
        AllNewsAdapter(this)
    }

    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcvnewsTitlesList.layoutManager = linearLayoutManager
        rcvnewsTitlesList.adapter = adapter
        allNewsFragmentPresenter.attachView(this)
        initSwipeToRefresh()
        showNewsOnStart(false)
    }

    private fun showNewsOnStart(needToRefresh: Boolean) {
        allNewsFragmentPresenter.country = arguments?.getString("country") ?: ""
        allNewsFragmentPresenter.category = arguments?.getString("category") ?: ""
        allNewsFragmentPresenter.q = arguments?.getString("q") ?: ""
        allNewsFragmentPresenter.showNews(needToRefresh)
    }

    private fun initSwipeToRefresh() {
        swipeOnRef.setOnRefreshListener {
            swipeOnRef.isRefreshing = true
            showNewsOnStart(true)
        }
        swipeOnRef.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_green_light)
    }

    override fun goToPosition() {
        linearLayoutManager.scrollToPositionWithOffset(allNewsFragmentPresenter.position, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savePosition()
        rcvnewsTitlesList.layoutManager = null
        allNewsFragmentPresenter.detachView()
    }

    override fun showAllNews(list: List<DBArticlesItem>) {
        adapter.listOfNews = list
        adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        prBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prBar.visibility = View.GONE
        swipeOnRef.isRefreshing = false
    }

    override fun showError(text: String) {
        when (text) {
            NO_INTERNET -> toast(getString(R.string.no_internet))
            ERROR_FROM_NET -> toast(getString(R.string.server_error))
        }
    }

    override fun goToDetail(url: String) {
        savePosition()
        allNewsFragmentPresenter.router.fragmentManager = (activity as MainActivity).supportFragmentManager
        allNewsFragmentPresenter.goToNewDetails(url)
    }

    private fun savePosition() {
        allNewsFragmentPresenter.position = linearLayoutManager.findFirstVisibleItemPosition()
    }
}