package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable

interface NewsDataSource {

//    fun getNewsFromBD(): Observable<List<DBArticlesItem>>
//
//    fun getNewsFromBD(restInteractor: RestInteractor): List<DBArticlesItem>

    fun getNews(): List<DBArticlesItem>

//    fun getNewsFromNetwork(restInteractor: RestInteractor): Observable<NewsHeadLinesResponse>

//    fun getNews(restInteractor: RestInteractor)
}