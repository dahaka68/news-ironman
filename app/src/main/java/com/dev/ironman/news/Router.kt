package com.dev.ironman.news

import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.RouterTarget


class Router {

    lateinit var routerTarget: RouterTarget

    fun initWith(routerTarget: RouterTarget) {
        this.routerTarget = routerTarget
    }

    fun showAllNewsFragment() {
        routerTarget.doTransaction(AllNewsFragment(), false)
    }
}