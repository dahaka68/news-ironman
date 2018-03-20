package com.dev.ironman.news.di.module

import android.content.Context
import com.dev.ironman.news.data.dao.AppDatabase
import com.dev.ironman.news.data.dao.NewsDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideNewsDAO(db: AppDatabase): NewsDAO {
        return db.newsDao()
    }
}