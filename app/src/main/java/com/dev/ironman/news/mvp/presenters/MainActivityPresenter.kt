package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.Router
import com.dev.ironman.news.mvp.views.MainActivityView

class MainActivityPresenter(val router: Router) : IPresenter<MainActivityView> {

	var view: MainActivityView? = null

	override fun attachView(view: MainActivityView) {
		this.view = view
		if (!view.isTablet() && router.isNotFragmentsInContainer()) {
			router.showAllNewsFragment()
		}
		else if (view.isTablet() && router.isCurFragNews()) {
			router.remove()
		}
	}

	override fun detachView() {
		view = null
	}

	fun loadNewsWithFilter(){
		router.showNewsFragment(view?.takeFilterParams())
	}

	fun isNotFragmentsInContainer() = router.isNotFragmentsInContainer()
}

