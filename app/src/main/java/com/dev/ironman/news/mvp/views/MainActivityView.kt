package com.dev.ironman.news.mvp.views

interface MainActivityView {

    fun isTablet(): Boolean

    fun takeFilterParams(): Map<String, String>

}