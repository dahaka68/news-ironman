package com.dev.ironman.news.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.ironman.news.R
import com.dev.ironman.news.data.dbModels.DBArticlesItem
import com.dev.ironman.news.rest.restModels.ArticlesItem
import com.dev.ironman.news.ui.IDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.new_holder.view.*

class AllNewsAdapter(private val iDetail: IDetail) : RecyclerView.Adapter<AllNewsAdapter.NewHolder>() {

    lateinit var listOfNews: List<DBArticlesItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val cv: CardView = (LayoutInflater.from(parent.context).inflate(R.layout.new_holder, parent, false) as CardView)
        return NewHolder(parent.context, cv, iDetail)
    }

    override fun getItemCount(): Int {
        return try {
            listOfNews.size
        } catch (ex: Exception) {
            0
        }
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        holder.setNewsItem(listOfNews[position])
    }

    class NewHolder(private val context: Context, private val cardView: CardView, private val iDetail: IDetail) : RecyclerView.ViewHolder(cardView) {

        fun setNewsItem(new: DBArticlesItem) {
            cardView.tvAutor.text = "Author: ${new.author}"
            cardView.tvDescription.text = new.description
            cardView.tvTitle.text = new.title
            cardView.link.text = new.url
            Picasso.with(context)
                    .load(new.urlToImage)
                    .placeholder(R.mipmap.ic_nophoto)
                    .error(R.mipmap.ic_error)
                    .into(cardView.ivPhoto)
            cardView.linlayNew.setOnClickListener({ iDetail.goToDetail(cardView.link.text.toString()) })
        }
    }
}