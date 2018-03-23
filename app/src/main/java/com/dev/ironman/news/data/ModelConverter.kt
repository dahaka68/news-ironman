package com.dev.ironman.news.data

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse

//нужно придумать лучший метод для конвертации чтобы избежаь !!
fun convertRestToDB(restItems: List<ArticlesItem>): List<DBArticlesItem> {
    val list: MutableList<DBArticlesItem> = ArrayList()
    for (item in restItems) {
        //Log.d("tttt", "\n\nConvert Item: ${item}")

        list.add(DBArticlesItem(
                0,
//                item.source.id.toInt(),
//                item.publishedAt,
//                item.author,
//                item.urlToImage,
//                item.description,
//                item.title,
//                item.url,
                "pub",
                "aut",
                "urlIma",
                "dec",
                "t",
                "url",
                0))
    }
    return list
}

fun convertTOFavourite(item: ArticlesItem): DBArticlesItem {
    return DBArticlesItem(0, item.publishedAt, item.author, item.urlToImage,
            item.description, item.title, item.url, 1)
}

fun convertDBToRest(dbItems: List<DBArticlesItem>): NewsHeadLinesResponse {
    val list: MutableList<ArticlesItem> = ArrayList()
    for (item in dbItems) {
        list.add(ArticlesItem(item.publishedAt, item.author, item.urlToImage, item.description, item.title, item.url))
    }
    return NewsHeadLinesResponse(list)
}


