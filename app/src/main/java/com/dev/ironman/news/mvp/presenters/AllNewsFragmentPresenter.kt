package com.dev.ironman.news.mvp.presenters

import android.util.Log
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import javax.inject.Inject

class AllNewsFragmentPresenter @Inject constructor(
        private val newsRepository: NewsRepository,
        val router: Router
) : IPresenter<AllNewsFragmentView> {

    var position = 0
    var view: AllNewsFragmentView? = null

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
        view.showProgress()
    }

    override fun detachView() {
        view = null
    }

    fun showNews() {
        Log.d("tag", newsRepository.getNews().size.toString())
//        view?.showAllNews(newsRepository.getNews())


//        val list = newsRepository.getNews()
//        Log.d("list", list.toString())
//        view?.showAllNews(list)
//        this.view?.showAllNews(newsRepository.getNewsFromBD(restInteractor))
//        val list: MutableList<DBArticlesItem> = ArrayList()
//        Executors.newSingleThreadExecutor().execute({ list.addAll(newsDAO.allArticles) })
//        if (list.isEmpty()) {
//            newsDispos = restInteractor.getHeadLines("us", "business")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            {
//                                convertRestToDB(it.articles).forEach { newsDAO.insertAllArticles(it) }
//                                view?.showAllNews(newsDAO.allArticles)
//                                view?.hideProgress()
//                                view?.goToPosition()
//                                newsDispos.dispose()
//                            },
//                            {
//                                view?.hideProgress()
//                                newsDispos.dispose()
//                            }
//                    )
//        } else {
//            view?.showAllNews(list)
//        }
    }

    fun goToNewDetails(url: String) {
        router.showDetailWebViewFragment(url)
    }
}