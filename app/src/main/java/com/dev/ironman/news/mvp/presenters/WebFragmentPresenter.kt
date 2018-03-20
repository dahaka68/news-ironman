package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.mvp.views.WebFragmentView


class WebFragmentPresenter : IPresenter<WebFragmentView> {

    var view: WebFragmentView? = null

    override fun attachView(view: WebFragmentView) {
        this.view = view
        view.showContent()
    }

    override fun detachView() {
        view = null
    }
}