package com.dev.ironman.news.di.components


import android.app.Application
import com.dev.ironman.news.App
import com.dev.ironman.news.di.modules.AppModule
import com.dev.ironman.news.di.modules.MainModule
import com.dev.ironman.news.ui.AllNewsFragment
import com.dev.ironman.news.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(fragment: AllNewsFragment)
}