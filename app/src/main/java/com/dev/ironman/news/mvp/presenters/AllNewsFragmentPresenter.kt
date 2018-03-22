package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AllNewsFragmentPresenter @Inject constructor(
        private val newsDAO: NewsDAO,
        private val restInteractor: RestInteractor,
        val router: Router
) : IPresenter<AllNewsFragmentView> {
    var position = 0

    private lateinit var newsDispos: Disposable
    var view: AllNewsFragmentView? = null

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
        view.showProgress()
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
                            convertRestToDB(it.articles).forEach { newsDAO.insertAllArticles(it) }
                            view?.showAllNews(newsDAO.allArticles)
                            view?.hideProgress()
                            view?.goToPosition()
                            newsDispos.dispose()
                        },
                        {
                            view?.hideProgress()
                            newsDispos.dispose()
                        }
                )
    }

    fun goToNewDetails(url: String) {
        router.showDetailWebViewFragment(url)
    }
}