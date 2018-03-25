package com.dev.ironman.news.rest.restModels

import com.google.gson.annotations.SerializedName

data class ArticlesItem(

        @field:SerializedName("publishedAt")
        val publishedAt: String? = "",

        @field:SerializedName("author")
        val author: String? = "",

        @field:SerializedName("urlToImage")
        val urlToImage: String? = "",

        @field:SerializedName("description")
        val description: String? = "",

        @field:SerializedName("title")
        val title: String? = "",

        @field:SerializedName("url")
        val url: String? = ""
)