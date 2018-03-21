package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.*

@Entity(tableName = "articles")
data class DBArticlesItem(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "publishedAt")
        var publishedAt: String? = null,

        @ColumnInfo(name = "author")
        var author: String? = null,

        @ColumnInfo(name = "urlToImage")
        var urlToImage: String? = null,

        @ColumnInfo(name = "description")
        var description: String? = null,
        @Ignore
        @Embedded
        var DBSource: DBSource? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "url")
        var url: String? = null
)