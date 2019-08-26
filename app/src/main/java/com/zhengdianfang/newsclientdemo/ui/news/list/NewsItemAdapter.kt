package com.zhengdianfang.newsclientdemo.ui.news.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhengdianfang.newsclientdemo.R
import com.zhengdianfang.newsclientdemo.model.News

class NewsItemAdapter: RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder>() {

    private val newsList = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        return NewsItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun updateNews(newData: List<News>) {
        if (newData.isNotEmpty()) {
            newsList.clear()
            newsList.addAll(newData)
            notifyDataSetChanged()
        }
    }

    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            val titleView = itemView.findViewById<TextView>(R.id.titleView)
            val bannerImage = itemView.findViewById<ImageView>(R.id.bannerImage)
            titleView.text = news.title
            Glide.with(itemView.context).load(news.thumbnail1).into(bannerImage)
        }
    }
}

