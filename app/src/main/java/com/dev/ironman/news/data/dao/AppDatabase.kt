package com.dev.ironman.news.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dev.ironman.news.data.dbModels.DBArticlesItem

@Database(entities = [DBArticlesItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDAO
}

