package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.ColumnInfo.*
import android.arch.persistence.room.*


@Entity(tableName = "articles")
data class DBArticlesItem(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "publishedAt", typeAffinity = TEXT)
        var publishedAt: String = "",

        @ColumnInfo(name = "author", typeAffinity = TEXT)
        var author: String = "",

        @ColumnInfo(name = "urlToImage", typeAffinity = TEXT)
        var urlToImage: String = "",

        @ColumnInfo(name = "description", typeAffinity = TEXT)
        var description: String = "",

        @Embedded
        var DBSource: DBSource = DBSource(0, "", ""),

        @ColumnInfo(name = "title", typeAffinity = TEXT)
        var title: String = "",

        @ColumnInfo(name = "url", typeAffinity = TEXT)
        var url: String = "",

        @ColumnInfo(name = "favourite")
        var favourite: Int = 0
)