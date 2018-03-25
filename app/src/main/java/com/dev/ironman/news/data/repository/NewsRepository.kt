package com.dev.ironman.news.data.repository

import com.dev.ironman.news.data.convertDBToRest
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import com.dev.ironman.news.util.NetwrorkVerify
import io.reactivex.Observable
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO,
                                         private val restInteractor: RestInteractor,
                                         private val netCheck: NetwrorkVerify) : NewsDataSourcerable {

	//получаем данные     возвращаем обсервбл
	//READ this - I've changed to Koltin style
	//TODO нужно добавить проверку .isEmpty из сети и базы Ночью сервак не работал и все легло
	//TODO получается после каждого переворота устройства проверка на сеть, а надо проверять сам сервер, а не сеть
	override fun getHeadLines(country: String,
	                          category: String): Observable<NewsHeadLinesResponse> =
			if (netCheck.checkInternet()) {
				restInteractor.getHeadLines(country, category)//из интернета
			} else {
				newsDAO.allArticles.map { convertDBToRest(it) }.toObservable()//из базы
			}


	//сохраняем в базу данных
	//Read this: Simple case of a kotlin coroutine - runBlocking stops the running of method until "join"
	//that's way we don't use it instead of RX here
	//it's not good practice but it's the simpliest way now
	fun saveInCache(dbArticlesItemList: List<DBArticlesItem>) = runBlocking {
		val writingToDbjob = launch {
			//TODO try catch were here
			dbArticlesItemList.forEach { newsDAO.insertAllArticles(it) }
		}
		writingToDbjob.join()
	}

	//TODO: нужно сделать логику, когда будет из БД, а когда из интернета
	// I've added simple method to verify if SIMPLE wifi is, and mobile network (but not internet!)
	// and it in SaveToCash method
	//fun isNetWorkAvailable() = netCheck.checkInternet()

}
