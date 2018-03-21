package com.dev.ironman.news.mvp.views

import com.dev.ironman.news.rest.restModels.ArticlesItem


interface AllNewsFragmentView {

    fun showAllNews(list: List<ArticlesItem?>?)

    fun showProgress()

    fun hideProgress()

    fun goToPosition()
}