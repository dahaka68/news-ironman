package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.Router
import com.dev.ironman.news.mvp.views.MainActivityView
import com.dev.ironman.news.ui.RouterTarget


class MainActivityPresenter(val router: Router) : IPresenter<MainActivityView> {

    var view: MainActivityView? = null

    override fun attachView(view: MainActivityView) {
        this.view = view
        router.initWith(view as RouterTarget)
        router.showAllNewsFragment()
    }

    override fun detachView() {
        view = null
    }
}