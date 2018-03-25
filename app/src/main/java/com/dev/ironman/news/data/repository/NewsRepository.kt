package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO,
                                         private val restInteractor: RestInteractor) : NewsDataSourcerable {

    fun requestNewsFromNet(country: String, category: String): Observable<NewsHeadLinesResponse> {
        return restInteractor.getHeadLines(country, category)
                .subscribeOn(Schedulers.io())
                .doOnNext({ convertRestToDB(it.articles).forEach { newsDAO.insertAllArticles(it)} })
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNews(country: String, category: String): List<DBArticlesItem> {
        val list = mutableListOf<DBArticlesItem>()
        runBlocking {
            val getArticles = launch {
                    list.addAll(newsDAO.allArticles)
            }
            getArticles.join()
        }
        return list
    }
}

