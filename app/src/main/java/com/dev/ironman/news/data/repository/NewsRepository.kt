package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.convertDBToRest
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor) : NewsDataSource {

    //получаем данные     возвращаем обсервбл
    override fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse> {
        val obs: Observable<NewsHeadLinesResponse>
        if (isNetWorkAvailable()) {
            obs = restInteractor.getHeadLines(country, category)//из интернета
        } else {
            obs = newsDAO.allArticles.map { convertDBToRest(it) }.toObservable()//из базы
        }
        return obs
    }

    //сохраняем в базу данных
    fun saveInCache(dbArticlesItem: DBArticlesItem) {
        newsDAO.insertAllArticles(dbArticlesItem)
    }

    //TODO: нужно сделать логику, когда будет из БД, а когда из интернета
    fun isNetWorkAvailable(): Boolean {
        return false
    }

}
