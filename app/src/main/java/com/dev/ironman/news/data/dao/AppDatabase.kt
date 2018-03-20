package com.dev.ironman.news.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dev.ironman.news.rest.restModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.DBSource

@Database(entities = [DBArticlesItem::class, DBSource::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDAO
}

