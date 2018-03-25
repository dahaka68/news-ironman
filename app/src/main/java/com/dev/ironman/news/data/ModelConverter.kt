package com.dev.ironman.news.data

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.rest.restModels.NewsHeadLinesResponse
import com.dev.ironman.news.util.*

fun convertRestToDB(restItems: List<ArticlesItem>) = restItems.map {
	DBArticlesItem(it.publishedAt?.convertDateToLong() ?: 0L,
			it.author ?: DEFAULT,
			it.urlToImage ?: NoIMAGE,
			it.description ?: NoDESCRIPTION,
			it.title ?: NoTITLE,
			it.url ?: NoURL, 0)
}

fun convertTOFavourite(item: ArticlesItem) =
		DBArticlesItem(item.publishedAt?.convertDateToLong() ?: 0L,
				item.author ?: NoAUTHOR,
				item.urlToImage ?: NoURL,
				item.description ?: NoDESCRIPTION,
				item.title ?: NoTITLE,
				item.url ?: NoURL,
				1)


fun convertDBToRest(dbItems: List<DBArticlesItem>): NewsHeadLinesResponse {
	val list: MutableList<ArticlesItem> = mutableListOf()

	dbItems.forEach {
		list.add(ArticlesItem(
				it.publishedAt.convertLongToString(),
				it.author,
				it.urlToImage,
				it.description,
				it.title,
				it.url))
	}

	return NewsHeadLinesResponse(list)
}

