package com.dev.ironman.news.mvp.presenters

interface IPresenter<in T> {

    fun attachView(view: T)
    fun detachView()
}