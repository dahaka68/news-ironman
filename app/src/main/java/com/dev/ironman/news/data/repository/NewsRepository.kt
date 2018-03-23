package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.utils.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository
@Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor)
    : NewsDataSource {
    private val appExecutors = AppExecutors()
    private lateinit var newsDispos: Disposable
    private lateinit var compositeDisposable: CompositeDisposable
    val list = mutableListOf<DBArticlesItem>()

    override fun getNews(): List<DBArticlesItem> {
        appExecutors.diskIO.execute {
            val news = newsDAO.allArticles
            appExecutors.mainThread.execute {
                if (news.isNotEmpty()) {
                    list.addAll(news)
                    Log.d("DB ", list.size.toString())
                } else {
                    requestNewsFromNet()
                }
            }
        }
        Log.d("DB2 ", list.size.toString())
        return list
    }

    private fun requestNewsFromNet() {
        compositeDisposable.add(restInteractor.getHeadLines("us", "business")
                .subscribeOn(Schedulers.io())
                .doOnNext({ for (t in convertRestToDB(it.articles)) newsDAO.insertAllArticles(t) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("onSuccess ", it.articles?.size.toString()) },
                        { t: Throwable? -> Log.d("onError", t?.message) })
        )
        getNews()
        compositeDisposable.dispose()
    }

}