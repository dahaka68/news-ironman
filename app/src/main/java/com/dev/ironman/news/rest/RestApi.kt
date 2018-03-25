package com.dev.ironman.news.rest

import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApi {

    @GET("top-headlines")
    fun getHeadLines(@Query("country") country: String,
                     @Query("category") category: String): Observable<NewsHeadLinesResponse>

    fun getHeadLines(@Query("q") keyWord: String,
                     @Query("country") country: String,
                     @Query("category") category: String): Observable<NewsHeadLinesResponse>

    @GET("top-headlines")
    fun getHeadLines(@Query("q") searchWord: String): Observable<NewsHeadLinesResponse>

    @GET("top-headlines")
    fun getHeadLines(@Query("country") country: String,
                     @Query("category") category: String,
                     @Query("sources") sources: String,
                     @Query("q") searchWord: String,
                     @Query("pageSize") pageSize: String,
                     @Query("page") page: String): Observable<NewsHeadLinesResponse>

    @GET("everything")
    fun getEveryThing(@Query("q") q: String): Observable<NewsHeadLinesResponse>

    @GET("everything")
    fun getEveryThing(@Query("q") searchWord: String,
                      @Query("from") from: String,
                      @Query("to") to: String,
                      @Query("sortedBy") sortedBy: String,
                      @Query("sources") sources: String,
                      @Query("domains") domains: String,
                      @Query("language") language: String,
                      @Query("pageSize") pageSize: String,
                      @Query("page") page: String): Observable<NewsHeadLinesResponse>
}