package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.*

@Entity(tableName = "articles")
data class DBArticlesItem(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "publishedAt")
        var publishedAt: String = "",

        @ColumnInfo(name = "author")
        var author: String = "",

        @ColumnInfo(name = "urlToImage")
        var urlToImage: String = "",

        @ColumnInfo(name = "description")
        var description: String = "",

        @Embedded
        var DBSource: DBSource = DBSource(0, "", ""),

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "url")
        var url: String = "",

        @ColumnInfo(name = "favourite")
        var favourite: Int = 0
)