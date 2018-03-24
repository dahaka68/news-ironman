package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.Single

interface NewsDataSource {

    fun getNewsFromBD(): Observable<List<DBArticlesItem>>

//    fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse>
//
//    fun getNews(country: String, category: String): Observable<List<DBArticlesItem>>

//    fun getNewsFromNetwork(restInteractor: RestInteractor): Observable<NewsHeadLinesResponse>

//    fun getNews(restInteractor: RestInteractor)
}