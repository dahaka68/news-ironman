package com.dev.ironman.news.rest.restModels

import com.google.gson.annotations.SerializedName

data class ArticlesItem(

        @field:SerializedName("publishedAt")
        val publishedAt: String? = null,

        @field:SerializedName("author")
        val author: String? = null,

        @field:SerializedName("urlToImage")
        val urlToImage: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("DBSource")
        val source: Source? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("url")
        val url: String? = null
)