package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Maybe
import io.reactivex.Observable

interface NewsDataSourcerable {

    fun getNews(q: String, country: String, category: String): List<DBArticlesItem>

//    fun getNewsByValue(value: String): List<DBArticlesItem>
//
//    fun getNewsByFilter(country: String, category: String): List<DBArticlesItem>
//
//    fun getNews(country: String, category: String): List<DBArticlesItem>
//
//    fun getNews(country: String, category: String): List<DBArticlesItem>
//
//    fun getNews(country: String, category: String): List<DBArticlesItem>

}
