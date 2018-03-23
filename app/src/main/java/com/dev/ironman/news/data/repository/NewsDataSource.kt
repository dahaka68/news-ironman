package com.dev.ironman.news.data.repository

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Maybe
import io.reactivex.Observable

interface NewsDataSource {

    fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse>
}
