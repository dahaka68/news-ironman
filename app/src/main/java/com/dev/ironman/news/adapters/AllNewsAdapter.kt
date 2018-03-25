package com.dev.ironman.news.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dev.ironman.news.R
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.ui.IDetail
import kotlinx.android.synthetic.main.new_holder.view.*

class AllNewsAdapter(private val iDetail: IDetail) : RecyclerView.Adapter<AllNewsAdapter.NewHolder>() {

	lateinit var listOfNews: List<DBArticlesItem>

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
		val cv: CardView = (LayoutInflater.from(parent.context).inflate(R.layout.new_holder, parent, false) as CardView)
		return NewHolder(parent.context, cv, iDetail)
	}

	override fun getItemCount() = if (this::listOfNews.isInitialized) listOfNews.size else 0


	override fun onBindViewHolder(holder: NewHolder, position: Int) {
		holder.setNewsItem(listOfNews[position])
	}

	class NewHolder(private val contextIn: Context, private val cardView: CardView, private val iDetail: IDetail) : RecyclerView.ViewHolder(cardView) {

		fun setNewsItem(news: DBArticlesItem) {

			cardView.apply {
				tvAuthor.text = "${context.resources.getString(R.string.Author)} ${news.author}"
				tvDescription.text = news.description
				tvTitle.text = news.title
				link.text = news.url
				newsContainer.setOnClickListener {
					iDetail.goToDetail(cardView.link.text.toString())
				}

				Glide.with(contextIn)
						.load(news.urlToImage)
						.apply(RequestOptions()
								.placeholder(R.mipmap.ic_nophoto)
								.error(R.mipmap.ic_error)
								.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
						.into(ivPhoto)
			}
		}
	}
}
