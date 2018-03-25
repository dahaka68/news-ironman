package com.dev.ironman.news.mvp.presenters

import com.dev.ironman.news.Router
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.views.AllNewsFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllNewsFragmentPresenter @Inject constructor(val newsRepository: NewsRepository,
                                                   val router: Router) : IPresenter<AllNewsFragmentView> {

    //TODO  где это используется?
    var position = 0

    var q = ""
    var country = ""
    var category = ""

    private lateinit var newsDispos: Disposable

    //TODO
    var view: AllNewsFragmentView? = null

    override fun attachView(view: AllNewsFragmentView) {
        this.view = view
        view.showProgress()
    }

    override fun detachView() {
        view = null
    }

    fun showNews() {
        //ToDO: мы получили параметры q- ключевое слово, country - страна, category - категория
        // Todo: неоходимо передать их репозиторию, чтобы он достал по этим ключевым словам инфу
        newsDispos = newsRepository.getHeadLines(q, country, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.showAllNews(it.articles)
                            //сохраняем в кэш даже когда они у нас и так в кэше даже если нет сети?? TODO
                            // Todo: Женя, у меня подвисает метод сохранения в кэш
//							newsRepository.saveInCache(convertRestToDB(it.articles))//сохраняем данные в кэш
                            view?.hideProgress()
                            view?.goToPosition()
                            newsDispos.dispose()
                        },
                        {
                            view?.hideProgress()
                            newsDispos.dispose()
                        },
                        {
                            view?.hideProgress()
                            newsDispos.dispose()
                        }
                )
    }

    fun goToNewDetails(url: String) = router.showDetailWebViewFragment(url)

}