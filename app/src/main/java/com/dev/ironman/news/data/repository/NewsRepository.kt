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

    fun requestNewsFromNet(keyWord: String, country: String, category: String): Observable<NewsHeadLinesResponse> {
        val observable =
                when {
                    keyWord.isEmpty() && country.isEmpty() && category.isEmpty() -> restInteractor.getHeadLines("ru", "general")
                    keyWord.isEmpty() -> restInteractor.getHeadLines(country, category)
                    else -> restInteractor.getHeadLines(keyWord)
                }
        return observable
                .subscribeOn(Schedulers.io())
                .doOnNext({ convertRestToDB(it.articles).forEach { newsDAO.insertAllArticles(it) } })
                .observeOn(AndroidSchedulers.mainThread())
    }

//    override fun getHeadLines(keyWord: String,
//                              country: String,
//                              category: String): Observable<NewsHeadLinesResponse> =
//            if (netCheck.checkInternet()) {
//                if (keyWord.isEmpty() && country.isEmpty() && category.isEmpty()) {
//                    restInteractor.getHeadLines("ru", "general")//из интернета по дефолтным категории и стране если запрос без фильтра
//                } else if (keyWord.isEmpty()) {
//                    restInteractor.getHeadLines(country, category)//из интернета по категории и стране из фильтра
//                } else {
//                    restInteractor.getHeadLines(keyWord)//из интернета по ключевому слову из фильтра
//                }
//            } else {
//                newsDAO.allArticles.map { convertDBToRest(it) }.toObservable()//из базы
//            }

    override fun getNews(q: String, country: String, category: String): List<DBArticlesItem> {
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

