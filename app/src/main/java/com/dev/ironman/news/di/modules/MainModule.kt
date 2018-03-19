package com.dev.ironman.news.di.modules

import com.dev.ironman.news.Router
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.RestService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideRestService() = RestService()

    @Provides
    @Singleton
    fun provideRestIteractor(restService: RestService) = RestInteractor(restService)

    @Provides
    @Singleton
    fun provideMainActivityPresenter(router: Router) = MainActivityPresenter(router)

    @Provides
    @Singleton
    fun provideAllNewsFragmentPresenter(restInteractor: RestInteractor) = AllNewsFragmentPresenter(restInteractor)

    @Provides
    @Singleton
    fun provideRouter() = Router()

}