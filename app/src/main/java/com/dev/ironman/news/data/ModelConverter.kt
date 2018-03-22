package com.dev.ironman.news.data

import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.data.dbModels.DBArticlesItem

//нужно придумать лучший метод для конвертации чтобы избежаь !!
fun convertRestToDB(restItems: List<ArticlesItem?>?): List<DBArticlesItem> {
    val list: MutableList<DBArticlesItem> = ArrayList()
    for (item in restItems!!) {
        list.add(DBArticlesItem(0, item?.publishedAt, item?.author, item?.urlToImage,
                item?.description, null, item?.title, item?.url, 0))
    }
    return list
}

fun convertTOFavourite(item: ArticlesItem?): DBArticlesItem {
    return DBArticlesItem(0, item?.publishedAt, item?.author, item?.urlToImage,
            item?.description, null, item?.title, item?.url, 1)
}


