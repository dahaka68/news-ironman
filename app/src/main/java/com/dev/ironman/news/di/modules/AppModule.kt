package com.dev.ironman.news.di.modules

import android.content.Context
import com.dev.ironman.news.App
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun provideApplication(app: App): Context
}