package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable

interface NewsDataSource {

    fun getNewsFromBD(): List<DBArticlesItem>

    fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse>

//    fun getNewsFromNetwork(restInteractor: RestInteractor): List<DBArticlesItem>

//    fun getNews(restInteractor: RestInteractor)
}