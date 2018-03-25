package com.dev.ironman.news.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import io.reactivex.Maybe

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticles(vararg dbArticlesItem: DBArticlesItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteArticle(dbArticlesItem: DBArticlesItem)

    @get:Query("SELECT * FROM articles")
    val allArticles: List<DBArticlesItem>

    @Query("SELECT * FROM articles WHERE favourite LIKE :isFavourites")
    fun favouritesArticles(isFavourites: Int): List<DBArticlesItem>
}