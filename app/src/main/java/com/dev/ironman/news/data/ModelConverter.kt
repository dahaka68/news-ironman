package com.dev.ironman.news.data

import android.util.Log
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.data.dbModels.DBSource
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import com.dev.ironman.news.rest.restModels.Source

fun convertRestToDB(restItems: List<ArticlesItem>): List<DBArticlesItem> {
//    val list: MutableList<DBArticlesItem> = ArrayList()
    val list = mutableListOf<DBArticlesItem>()
    restItems.mapTo(list) {
        DBArticlesItem(it.publishedAt.toLong(), it.author, it.urlToImage, it.description,
                 it.title, it.url, 0)
    }
    return list
}

fun convertTOFavourite(item: ArticlesItem): DBArticlesItem {
    return DBArticlesItem(item.publishedAt.toLong(), item.author, item.urlToImage,
            item.description, item.title, item.url, 1)
}

fun convertDBToRest(dbItems: List<DBArticlesItem>): NewsHeadLinesResponse {

    val list: MutableList<ArticlesItem> = ArrayList()
    for (item in dbItems) {
//        list.add(ArticlesItem(item.publishedAt, item.author, item.urlToImage, item.description, Source(item.DBSource.id, item.DBSource.name), item.title, item.url))
    }
    val newsHeadLinesResponse: NewsHeadLinesResponse = NewsHeadLinesResponse(list.size, list, "200")
    return newsHeadLinesResponse
}


