package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.Router
import com.dev.ironman.news.mvp.views.MainActivityView

class MainActivityPresenter(val router: Router) : IPresenter<MainActivityView> {

    var view: MainActivityView? = null

    override fun attachView(view: MainActivityView) {
        this.view = view
        router.fragmentManager = view.fragmentManager()
        router.showAllNewsFragment()
    }

    override fun detachView() {
        view = null
    }
}

