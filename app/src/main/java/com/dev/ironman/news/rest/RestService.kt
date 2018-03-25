package com.dev.ironman.news.rest

import com.dev.ironman.news.util.API_KEY
import com.dev.ironman.news.util.BASE_URL
import com.dev.ironman.news.util.HEADER
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestService {

	fun getRestApi(): RestApi {

		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY

		val httpClient = OkHttpClient.Builder()
		httpClient.addInterceptor(logging)
		httpClient.addInterceptor { chain ->
			val requestBuilder = chain.request().newBuilder().header(HEADER, API_KEY)
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