package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import javax.inject.Inject


class AllNewsFragmentPresenter
@Inject constructor(private val newsRepository: NewsRepository, val restInteractor: RestInteractor) : IPresenter<AllNewsFragmentView> {

    var view: AllNewsFragmentView? = null

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
        view.showProgress()
    }

    override fun detachView() {
        view = null
    }

    fun showNews() {
//        newsRepository.getNewsFromNetwork(restInteractor, view)
        newsRepository.getNewsFromBD(view)

    }
}