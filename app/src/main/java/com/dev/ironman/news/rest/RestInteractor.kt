package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable


class RestInteractor(val restService: RestService) {//RestService injected

    fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse> {
        return restService.getRestApi().getHeadLines(country, category)
    }

}