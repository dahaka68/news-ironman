package com.dev.ironman.news.mvp.presenters

import android.util.Log
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllNewsFragmentPresenter @Inject constructor(
        val newsRepository: NewsRepository,
        val router: Router
) : IPresenter<AllNewsFragmentView> {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
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
        newsRepository.requestNewsFromNet("us", "business")
        newsRepository.getNewsFromBD()
//        newsRepository.requestNewsFromNet("us", "business")
//
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.showAllNews(it)
                            Log.d("onView", it.toString())
                            view?.hideProgress()
                        },
                        { t -> Log.d("onError", t.message) })
    }

    fun goToNewDetails(url: String) {
        router.showDetailWebViewFragment(url)
    }
}