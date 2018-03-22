package com.dev.ironman.news.data.repository

import android.util.Log
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import io.reactivex.disposables.Disposable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDAO: NewsDAO) : NewsDataSource {

    private lateinit var newsDispos: Disposable

    override fun getNewsFromBD(): List<DBArticlesItem> {
        val list = mutableListOf<DBArticlesItem>()
        Executors.newSingleThreadExecutor().execute({ list.addAll(newsDAO.allArticles) })
        Log.d("tttt", newsDAO.allArticles.toString())
        return list
    }
}