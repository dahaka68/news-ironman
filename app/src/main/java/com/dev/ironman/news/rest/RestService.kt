package com.dev.ironman.news.rest

import com.dev.ironman.news.API_KEY
import com.dev.ironman.news.BASE_URL
import com.dev.ironman.news.HEADER
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestService {
    fun getRestApi(): RestApi {

        val httpClient = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder().header(HEADER, API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        return retrofit.create(RestApi::class.java)
    }
}