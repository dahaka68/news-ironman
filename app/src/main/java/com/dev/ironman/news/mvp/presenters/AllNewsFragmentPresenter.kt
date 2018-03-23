package com.dev.ironman.news.mvp.presenters

import android.util.Log
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AllNewsFragmentPresenter @Inject constructor(
        val newsRepository: NewsRepository,
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
        newsDispos = newsRepository.getHeadLines("us", "business")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.showAllNews(it.articles)
                            try {
                                //Log.d("tttt", "\n${it.articles[0]}")

                                val dbItems: List<DBArticlesItem> = convertRestToDB(it.articles)

                                for (item in dbItems) {
                                    newsRepository.saveInCache(item)//сохраняем данные в кэш
                                }
                            }catch (ex: Exception){
                                Log.d("tttt", "\n${ex.toString()}")
                            }

                            view?.hideProgress()
                            view?.goToPosition()
                            newsDispos.dispose()
                        },
                        {
                            Log.d("tttt", "\nError")
                            view?.hideProgress()
                            newsDispos.dispose()
                        }
                )
    }

    fun goToNewDetails(url: String) {
        router.showDetailWebViewFragment(url)
    }
}