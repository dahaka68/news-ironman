package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AllNewsFragmentPresenter(val restInteractor: RestInteractor) : IPresenter<AllNewsFragmentView> {

    var view: AllNewsFragmentView? = null

    private lateinit var newsDispos: Disposable

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun showNews() {
        newsDispos = restInteractor.getHeadLines("us", "business")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.showAllNews(it.articles)
                            newsDispos.dispose()
                        },
                        {
                            newsDispos.dispose()
                        }
                )
    }
}