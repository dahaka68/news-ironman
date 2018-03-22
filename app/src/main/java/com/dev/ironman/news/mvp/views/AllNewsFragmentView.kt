package com.dev.ironman.news.mvp.views

import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.ArticlesItem


interface AllNewsFragmentView {

    fun showAllNews(list: List<DBArticlesItem>)

    fun showProgress()

    fun hideProgress()
}