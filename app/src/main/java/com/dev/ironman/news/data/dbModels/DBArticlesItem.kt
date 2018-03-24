package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.ColumnInfo.*
import android.arch.persistence.room.*


@Entity(tableName = "articles")
data class DBArticlesItem(
        @PrimaryKey
        @ColumnInfo(name = "publishedAt")
        var publishedAt: Long = 0,

        @ColumnInfo(name = "author", typeAffinity = TEXT)
        var author: String = "",

        @ColumnInfo(name = "urlToImage", typeAffinity = TEXT)
        var urlToImage: String = "",

        @ColumnInfo(name = "description", typeAffinity = TEXT)
        var description: String = "",

        @ColumnInfo(name = "title", typeAffinity = TEXT)
        var title: String = "",

        @ColumnInfo(name = "url", typeAffinity = TEXT)
        var url: String = "",

        @ColumnInfo(name = "favourite")
        var favourite: Int = 0
)