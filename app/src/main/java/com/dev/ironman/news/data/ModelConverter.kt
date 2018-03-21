package com.dev.ironman.news.data

import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.data.dbModels.DBArticlesItem

fun convertRestToDB(restItems: MutableList<ArticlesItem?>?): MutableList<DBArticlesItem> {
    val list: MutableList<DBArticlesItem> = mutableListOf()


//    return restItems?.let {
//        it.map {
//            DBArticlesItem(0, it?.publishedAt, it?.author, it?.urlToImage, it?.description, null, it?.title, it?.url)
//        }
//    }


    for (item in restItems!!) {
        list.add(DBArticlesItem(0, item?.publishedAt, item?.author, item?.urlToImage, item?.description, null, item?.title, item?.url))
    }
    return list
}


