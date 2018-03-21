package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.App
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dao.AppDatabase
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.data.dbModels.DBSource
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.ArticlesItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.function.Consumer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  : NewsDataSource {

    private lateinit var newsDispos: Disposable
    val list: MutableList<DBArticlesItem> = mutableListOf()
    val newsDAO = App().database.newsDao()

    override fun getNewsFromBD(view: AllNewsFragmentView?) {
//        Executors.newSingleThreadExecutor().execute({ view?.showAllNews(newsDAO.getAllArticles()) })
        Log.d("tttttt", newsDAO.allArticles.toString())
        view?.hideProgress()
    }

    override fun getNewsFromNetwork(restInteractor: RestInteractor, view: AllNewsFragmentView?) {
        Executors.newSingleThreadExecutor().execute({
            newsDAO.insertArticle(DBArticlesItem(
                    0, "gffb", "fgh", "fhgh,", "fhh", DBSource(0, "gf"), "gfh", "gf"))
        })
        newsDispos = restInteractor.getHeadLines("us", "business")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            convertRestToDB(it.articles).forEach { newsDAO.insertArticle(it) }
                            for (article in convertRestToDB(it.articles)) {
                                list.add(article)
//                                newsDAO.insertArticle(article)
                            }
//                                newsDAO.insertAllArticles()

                            view?.showAllNews(newsDAO.allArticles)
                            view?.hideProgress()
                            newsDispos.dispose()
                        },
                        {
                            view?.hideProgress()
                            newsDispos.dispose()
                        }
                )
    }


}