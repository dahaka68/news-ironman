package com.dev.ironman.news.data

import android.util.Log
import com.dev.ironman.news.convertDateToLong
import com.dev.ironman.news.convertLongToString
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse

fun convertRestToDB(restItems: List<ArticlesItem>): List<DBArticlesItem> {
    val list = mutableListOf<DBArticlesItem>()

    for(i in restItems){
        Log.d("TAG", i.toString())
    }

    try {
        restItems.mapTo(list) {
            DBArticlesItem(it.publishedAt.convertDateToLong(), it?.author ?: "default", it.urlToImage, it.description, it.title, it.url, 0)

        }
    } catch (ex: Exception) {
        Log.d("myLogs", "$ex \n $list")
    }
    return list
}

fun convertTOFavourite(item: ArticlesItem): DBArticlesItem {
    return DBArticlesItem(item.publishedAt.convertDateToLong(),item?.author ?: "default", item.urlToImage,
            item.description, item.title, item.url, 1)
}

fun convertDBToRest(dbItems: List<DBArticlesItem>): NewsHeadLinesResponse {
    val list: MutableList<ArticlesItem> = ArrayList()
    for (item in dbItems) {
        list.add(ArticlesItem(item.publishedAt.convertLongToString(), item.author, item.urlToImage, item.description, item.title, item.url))
    }
    return NewsHeadLinesResponse(list)
}




