package com.dev.ironman.news.rest.restModels

import com.google.gson.annotations.SerializedName

data class NewsHeadLinesResponse(

        @field:SerializedName("totalResults")
        val totalResults: Int=404,

        @field:SerializedName("articles")
        val articles: List<ArticlesItem> = ArrayList(),

        @field:SerializedName("status")
        val status: String = ""
)