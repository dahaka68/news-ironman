package com.dev.ironman.news.data

import android.util.Log
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.data.dbModels.DBSource
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import com.dev.ironman.news.rest.restModels.Source

//нужно придумать лучший метод для конвертации чтобы избежаь !!
fun convertRestToDB(restItems: List<ArticlesItem>): List<DBArticlesItem> {
    val list: MutableList<DBArticlesItem> = ArrayList()
    for (item in restItems) {
        //Log.d("tttt", "\n\nConvert Item: ${item}")

        list.add(DBArticlesItem(
                0,
//                item.source.id.toInt(),
                "pub",
//                item.publishedAt,
//                item.author,
                "aut",
//                item.urlToImage,
                "urlIma",
                "dec",
//                item.description,
                DBSource(/*item.source.id.toInt(), item.source.name, item.source.id*/),
                "t",
//                item.title,
                //item.url,
                "url",
                0))
    }
    return list
}

fun convertTOFavourite(item: ArticlesItem): DBArticlesItem {
    return DBArticlesItem(0, item.publishedAt, item.author, item.urlToImage,
            item.description, DBSource(item.source.id.toInt(), item.source.name, item.source.id), item.title, item.url, 1)
}

fun convertDBToRest(dbItems: List<DBArticlesItem>): NewsHeadLinesResponse {

    val list: MutableList<ArticlesItem> = ArrayList()
    for (item in dbItems) {
        list.add(ArticlesItem(item.publishedAt, item.author, item.urlToImage, item.description, Source(item.DBSource.id, item.DBSource.name), item.title, item.url))
    }
    val newsHeadLinesResponse: NewsHeadLinesResponse = NewsHeadLinesResponse(list.size, list, "200")
    return newsHeadLinesResponse
}


