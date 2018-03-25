package com.dev.ironman.news.data.repository

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Maybe
import io.reactivex.Observable

interface NewsDataSourcerable {

    fun getHeadLines(keyWord: String, country: String, category: String): Observable<NewsHeadLinesResponse>
}
