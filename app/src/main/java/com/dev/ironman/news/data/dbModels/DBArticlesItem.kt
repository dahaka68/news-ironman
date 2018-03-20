package com.dev.ironman.news.rest.restModels

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class DBArticlesItem(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @ColumnInfo(name = "publishedAt")
        val publishedAt: String? = null,

        @ColumnInfo(name = "author")
        val author: String? = null,

        @ColumnInfo(name = "urlToImage")
        val urlToImage: String? = null,

        @ColumnInfo(name = "description")
        val description: String? = null,

        @Embedded
        @ColumnInfo(name = "DBSource")
        val DBSource: DBSource? = null,

        @ColumnInfo(name = "title")
        val title: String? = null,

        @ColumnInfo(name = "url")
        val url: String? = null
)