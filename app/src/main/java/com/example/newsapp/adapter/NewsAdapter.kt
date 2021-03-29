package com.example.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.db.entity.News
import com.example.newsapp.ui.AnimationActivity
import com.example.newsapp.ui.WebViewActivity
import com.google.android.material.button.MaterialButton
import java.util.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val newsList: ArrayList<News> = arrayListOf()
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.authorName.text = newsList[position].author
        holder.title.text = newsList[position].title
        holder.description.text = newsList[position].description
        Glide.with(mContext)
            .load(newsList[position].urlToImage)
            .centerInside()
            .into(holder.image)
        holder.fullNewsBtn.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("URL", newsList[position].url)
            intent.putExtra("TITLE", newsList[position].title)
            mContext.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, AnimationActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(news: List<News>) {
        newsList.clear()
        val sortedNewsList = news.sortedWith(compareBy { it.author.toLowerCase(Locale.ROOT) })
        newsList.addAll(sortedNewsList)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorName: AppCompatTextView = itemView.findViewById(R.id.tv_author_name)
        val title: AppCompatTextView = itemView.findViewById(R.id.tv_title)
        val image: ImageView = itemView.findViewById(R.id.iv_news_image)
        val description: AppCompatTextView = itemView.findViewById(R.id.tv_description)
        val fullNewsBtn: MaterialButton = itemView.findViewById(R.id.btn_see_full_news)
    }
}

