package com.dev.ironman.news.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.ironman.news.R
import com.dev.ironman.news.rest.restModels.ArticlesItem
import kotlinx.android.synthetic.main.new_holder.view.*


class AllNewsAdapter : RecyclerView.Adapter<AllNewsAdapter.NewHolder>() {

    lateinit var listOfNews: List<ArticlesItem?>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val cv: CardView = (LayoutInflater.from(parent.context).inflate(R.layout.new_holder, parent, false) as CardView)
        return NewHolder(cv)
    }

    override fun getItemCount(): Int {
        try {
            return listOfNews.size
        } catch (ex: Exception){
            return 0
        }
    }


    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        holder.setNew(listOfNews[position])
    }


    class NewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView) {

        fun setNew(new: ArticlesItem?) {
            cardView.tvAutor.text = "Author: ${new?.author}"
            cardView.tvDescription.text = new?.description
            cardView.tvTitle.text = new?.title
        }
    }
}