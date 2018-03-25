package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.convertRestToDB
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository
@Inject constructor(private val newsDAO: NewsDAO, private val restInteractor: RestInteractor)
    : NewsDataSource {
    private lateinit var newsDispos: Disposable
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val list = mutableListOf<DBArticlesItem>()

//    override fun getNewsFromBD(): Observable<List<DBArticlesItem>> {
//        compositeDisposable.add(Observable.just(1)
//                .subscribeOn(Schedulers.io())
//                .doOnNext({ for (t in convertRestToDB(it.articles)) newsDAO.insertAllArticles(t) })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    list.addAll(it)
//                }))
//        return
//    }

    //метод для забора данных из базы
    override fun getNewsFromBD(): Observable<List<DBArticlesItem>> {
        return newsDAO.getAllArticles().map { it }  // it это  List<DBArticlesItem>
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    //TODO нужно сделать метод который будет проверять данные из базы и если их нет, лезть в сеть и в итоге возвращать Observable
//    override fun getNews(country: String, category: String): Observable<List<DBArticlesItem>> {
//        val obs: Observable<List<DBArticlesItem>>
////        obs = Observable.just(list)
////                .subscribeOn(Schedulers.io())
////                .doOnNext { getNewsFromBD().filter { it.isEmpty() }}
////                .observeOn(AndroidSchedulers.mainThread())
////
////
//getNewsFromBD().repeatWhen {  }
//        Log.d("DB2 ", list.size.toString())
//        return obs
//    }

    //метод для забора данных из сети в базу
    fun requestNewsFromNet(country: String, category: String) {
        compositeDisposable.add(restInteractor.getHeadLines(country, category)
                .subscribeOn(Schedulers.io())
                //пробегаюсь по полученным данным, конвертирую в модель пригодную для бд и заношу туда данные
                .doOnNext({ for (t in convertRestToDB(it.articles)) newsDAO.insertAllArticles(t) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("onSuccess ", it.articles.size.toString())
                            compositeDisposable.dispose()
                        },
                        { t: Throwable? ->
                            Log.d("onError", t?.message)
                            compositeDisposable.dispose()
                        })
        )
    }

//    fun requestNewsFromNet(country: String, category: String): Observable<NewsHeadLinesResponse> {
//        return restInteractor.getHeadLines(country, category)
//                .subscribeOn(Schedulers.io())
////                .doOnNext({ for (t in convertRestToDB(it.articles)) newsDAO.insertAllArticles(t) })
//                .doOnNext({getNewsFromBD()})
//                .observeOn(AndroidSchedulers.mainThread())
//    }

    //TODO: нужно сделать логику, когда будет из БД, а когда из интернета
    fun isNetWorkAvailable(): Boolean {
        return false
    }
}

