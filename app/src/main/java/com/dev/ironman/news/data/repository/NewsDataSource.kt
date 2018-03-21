package com.dev.ironman.news.data.repository

import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import com.dev.ironman.news.rest.RestInteractor

interface NewsDataSource {

    fun getNewsFromBD(view: AllNewsFragmentView?)

    fun getNewsFromNetwork(restInteractor: RestInteractor, view: AllNewsFragmentView?)
}