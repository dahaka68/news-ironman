package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable


class RestInteractor(val restService: RestService) {

	fun getHeadLines(keyWord: String, country: String, category: String): Observable<NewsHeadLinesResponse> =
			restService.getRestApi().getHeadLines(keyWord, country, category)

}