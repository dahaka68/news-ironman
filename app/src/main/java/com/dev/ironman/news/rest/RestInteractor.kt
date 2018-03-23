package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Maybe


class RestInteractor(val restService: RestService) {

    fun getHeadLines(country: String, category: String): Maybe<NewsHeadLinesResponse> {
        return restService.getRestApi().getHeadLines(country, category)
    }

}