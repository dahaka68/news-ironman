package com.dev.ironman.news.rest.restModels

import com.google.gson.annotations.SerializedName

data class NewsHeadLinesResponse(
        @field:SerializedName("articles")
        val articles: List<ArticlesItem> = listOf()
)