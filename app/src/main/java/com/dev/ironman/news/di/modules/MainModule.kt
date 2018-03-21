package com.dev.ironman.news.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.dev.ironman.news.App
import com.dev.ironman.news.Router
import com.dev.ironman.news.data.dao.AppDatabase
import com.dev.ironman.news.data.dao.NewsDAO
import com.dev.ironman.news.data.repository.NewsRepository
import com.dev.ironman.news.mvp.presenters.AllNewsFragmentPresenter
import com.dev.ironman.news.mvp.presenters.MainActivityPresenter
import com.dev.ironman.news.mvp.presenters.WebFragmentPresenter
import com.dev.ironman.news.rest.RestInteractor
import com.dev.ironman.news.rest.RestService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
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
    fun provideAllNewsFragmentPresenter(newsRepository: NewsRepository, restInteractor: RestInteractor) = AllNewsFragmentPresenter(newsRepository, restInteractor)

    @Provides
    @Singleton
    fun provideWebFragmentPresenter() = WebFragmentPresenter()

    @Provides
    @Singleton
    fun provideRouter() = Router()


    @Singleton
    @Provides
    fun provideRepository() = NewsRepository()

    @Singleton
    @Provides
    fun provideNewDao(db: AppDatabase) = db.newsDao()

    @Provides
    @Singleton
    fun provideDb(context: App) =
            Room.databaseBuilder(context, AppDatabase::class.java, "articles").allowMainThreadQueries().build()
}