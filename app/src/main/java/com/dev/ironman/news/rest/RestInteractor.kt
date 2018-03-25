package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable


class RestInteractor(val restService: RestService) {

    fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse> =
            restService.getRestApi().getHeadLines(country, category)

    fun getHeadLines(keyWord: String): Observable<NewsHeadLinesResponse> =
            restService.getRestApi().getHeadLines(keyWord)

}