package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApi {
    @GET("top-headlines")
    fun getHeadLines(@Query("country") country: String,
                     @Query("category") category: String,
                     @Query("apiKey") apiKey: String): Observable<NewsHeadLinesResponse>

}