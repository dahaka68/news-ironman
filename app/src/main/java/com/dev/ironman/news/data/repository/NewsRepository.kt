package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.convertDBToRest
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.utils.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository
@Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor)
    : NewsDataSource {
    private val appExecutors = AppExecutors()
    private lateinit var newsDispos: Disposable
    private lateinit var compositeDisposable: CompositeDisposable
    val list = mutableListOf<DBArticlesItem>()

//    override fun getNewsFromBD(): Observable<List<DBArticlesItem>> {
//        compositeDisposable.add(Observable.just(1)
//                .subscribeOn(Schedulers.io())
//                .doOnNext({ list.addAll(newsDAO.allArticles) })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    list.addAll(it)
//                }))
//        return
//    }

    //метод для забора данных из базы
    override fun getNewsFromBD(): Observable<List<DBArticlesItem>> = newsDAO.allArticles

    //TODO нужно сделать метод который будет проверять данные из базы и если их нет, лезть в сеть и в итоге возвращать Observable
    override fun getNews(country: String, category: String): Observable<List<DBArticlesItem>> {
        val obs: Observable<List<DBArticlesItem>>
//        obs = Observable.just(list)
//                .subscribeOn(Schedulers.io())
//                .doOnNext { getNewsFromBD().filter { it.isEmpty() }}
//                .observeOn(AndroidSchedulers.mainThread())
//
//

        Log.d("DB2 ", list.size.toString())
        return obs
    }

    //метод для забора данных из сети в базу
    fun requestNewsFromNet(country: String, category: String) {
        compositeDisposable.add(restInteractor.getHeadLines(country, category)
                .subscribeOn(Schedulers.io())
                .doOnNext({ for (t in convertRestToDB(it.articles)) newsDAO.insertAllArticles(t) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("onSuccess ", it.articles.size.toString()) },
                        { t: Throwable? -> Log.d("onError", t?.message) })
        )
        compositeDisposable.dispose()
    }

    //Это Паша писал, просто пока оставим, вдруг пригодится
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
    

    //TODO: нужно сделать логику, когда будет из БД, а когда из интернета
    fun isNetWorkAvailable(): Boolean {
        return false
    }
}

