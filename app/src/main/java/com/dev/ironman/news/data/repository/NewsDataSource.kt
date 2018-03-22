package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor

interface NewsDataSource {

    fun getNewsFromBD(): List<DBArticlesItem>

//    fun getNewsFromNetwork(restInteractor: RestInteractor): List<DBArticlesItem>

//    fun getNews(restInteractor: RestInteractor)
}