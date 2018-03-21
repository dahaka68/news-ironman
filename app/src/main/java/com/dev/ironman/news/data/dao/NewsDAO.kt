package com.dev.ironman.news.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dev.ironman.news.data.dbModels.DBArticlesItem

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticles(vararg dbArticlesItem: DBArticlesItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(dbArticlesItem: DBArticlesItem)

    @get:Query("SELECT * FROM articles")
//    fun getAllArticles(): MutableList<DBArticlesItem>
    val allArticles: MutableList<DBArticlesItem>

//    @Query("SELECT * FROM articles")
//    fun getFavouritesArticles(isFavourites: Boolean): MutableList<DBArticlesItem>
}