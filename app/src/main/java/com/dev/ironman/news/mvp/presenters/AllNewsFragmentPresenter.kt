package com.dev.ironman.news.mvp.presenters

import android.util.Log
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.util.ERROR_FROM_NET
import com.dev.ironman.news.util.NO_INTERNET
import com.dev.ironman.news.util.NetworkVerify
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllNewsFragmentPresenter
@Inject constructor(private val newsRepository: NewsRepository, val router: Router,
                    private val netCheck: NetworkVerify) : IPresenter<AllNewsFragmentView> {

    var position = 0
    private val cashList = mutableListOf<DBArticlesItem>()
    var q = ""
    var country = ""
    var category = ""

    var view: AllNewsFragmentView? = null

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
        view.showProgress()
    }

    override fun detachView() {
        view = null
    }

    fun showNews(swipeToRef: Boolean) {
        if (!swipeToRef) {
            view?.showProgress()
        }
        if (cashList.isNotEmpty() && !swipeToRef) {
            view?.showAllNews(cashList)
            view?.hideProgress()
        } else {
            if (newsRepository.getNews(q, country, category).isNotEmpty() && !swipeToRef) {
                view?.showAllNews(newsRepository.getNews(q, country, category))
                cashList.addAll(newsRepository.getNews(q, country, category))
                view?.hideProgress()
            } else {
                if (netCheck.isNetWorkAvailable()) {
                    newsRepository.requestNewsFromNet(q, country, category)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    {
                                        val listNews = convertRestToDB(it.articles)
                                        view?.showAllNews(listNews)
                                        cashList.clear()
                                        cashList.addAll(listNews)
                                        view?.hideProgress()
                                    },
                                    {
                                        Log.d("onError", it.message)
                                        view?.hideProgress()
                                        view?.showError(ERROR_FROM_NET)
                                    })
                } else {
                    view?.hideProgress()
                    view?.showError(NO_INTERNET)
                }
            }
        }
    }

    fun goToNewDetails(url: String) = router.showDetailWebViewFragment(url)
}