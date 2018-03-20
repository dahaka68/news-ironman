package com.dev.ironman.news.rest.restModels

import com.google.gson.annotations.SerializedName

data class NewsHeadLinesResponse(

		@field:SerializedName("totalResults")
	val totalResults: Int? = null,

		@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

		@field:SerializedName("status")
	val status: String? = null
)