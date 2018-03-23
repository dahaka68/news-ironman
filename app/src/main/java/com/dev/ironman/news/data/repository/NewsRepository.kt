package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.convertDBToRest
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor) : NewsDataSource {

    override fun getNewsFromBD(): List<DBArticlesItem> {
        val list = mutableListOf<DBArticlesItem>()
       // Executors.newSingleThreadExecutor().execute({ list.addAll(newsDAO.allArticles) })
        list.addAll(newsDAO.allArticles)
        return list
    }

    //получаем данные     возвращаем обсервбл
    override fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse> {
        val obs: Observable<NewsHeadLinesResponse>
        if (isNetWorkAvailable()) {
            obs = restInteractor.getHeadLines(country, category)//из интернета
        } else {
            obs = Observable.just(convertDBToRest(getNewsFromBD()))//из базы данных

//            obs = Observable.create({ e ->
//                e.onNext(convertDBToRest(getNewsFromBD()))
//                e.onComplete()
//            })
        }
        return obs
    }

    //сохраняем в базу данных
    fun saveInCache(dbArticlesItem: DBArticlesItem){
        newsDAO.insertAllArticles(dbArticlesItem)
    }


    //TODO: нужно сделать логику, когда будет из БД, а когда из интернета
    fun isNetWorkAvailable(): Boolean {
        return false
    }

}
