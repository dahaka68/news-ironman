package com.dev.ironman.news.rest

import com.dev.ironman.news.BASE_URL
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
//        httpClient.addInterceptor { chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder().addHeader("apiKey", "ed410f2fab8a4663a19c5d2e8fe6850a")
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }

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