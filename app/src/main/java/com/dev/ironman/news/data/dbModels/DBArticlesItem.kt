package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.ColumnInfo.*
import android.arch.persistence.room.*


@Entity(tableName = "articles")
data class DBArticlesItem(

        @PrimaryKey
        @ColumnInfo(name = "publishedAt")
        var publishedAt: Long = 0L,

        @ColumnInfo(name = "author")
        var author: String = "",

        @ColumnInfo(name = "urlToImage")
        var urlToImage: String = "",

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "url")
        var url: String = "",

        @ColumnInfo(name = "favourite")
        var favourite: Int = 0
)