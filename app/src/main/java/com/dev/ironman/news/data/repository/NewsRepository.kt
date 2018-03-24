package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.convertDBToRest
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor) : NewsDataSource {

    var disp: Disposable? = null

    //получаем данные     возвращаем обсервбл
    override fun getHeadLines(country: String, category: String): Observable<NewsHeadLinesResponse> {
        val obs: Observable<NewsHeadLinesResponse>
        if (isNetWorkAvailable()) {
            obs = restInteractor.getHeadLines(country, category)//из интернета
        } else {
            obs = newsDAO.allArticles.map { convertDBToRest(it) }.toObservable()//из базы
        }
        return obs
    }

    //сохраняем в базу данных
    fun saveInCache(dbArticlesItemList: List<DBArticlesItem>) {
//        disp = Observable.just(dbArticlesItemList)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(
//                        {
//                            for (item in it) {
//                                newsDAO.insertAllArticles(item)
//                            }
//                            disp?.dispose()
//                        },
//                        {
//                            it.toString()
//                        }
//                )
        try {
            for (item in dbArticlesItemList) {
                newsDAO.insertAllArticles(item)
            }
        }catch (ex: Exception){
            Log.d("myLogs", "$ex")
        }
    }

    //TODO: нужно сделать логику, когда будет из БД, а когда из интернета
    fun isNetWorkAvailable(): Boolean {
        return false
    }

}
