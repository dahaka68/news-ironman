package com.dev.ironman.news.rest

import com.dev.ironman.news.API_KEY
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import retrofit2.http.Query


class RestInteractor(val restService: RestService) {

    fun getHeadLines(country: String,
                     category: String): Observable<NewsHeadLinesResponse> {
        return restService.getRestApi().getHeadLines(country, category, API_KEY)
    }

}